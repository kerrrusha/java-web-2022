package com.kerrrusha.lab234.service.money;

import com.google.gson.Gson;
import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.billing.BillingDao;
import com.kerrrusha.lab234.dao.blocked_money_account.BlockedMoneyAccountDao;
import com.kerrrusha.lab234.dao.moneycard.MoneyCardDao;
import com.kerrrusha.lab234.factory.GsonFactory;
import com.kerrrusha.lab234.factory.MoneyCardFactory;
import com.kerrrusha.lab234.model.*;
import com.kerrrusha.lab234.service.money.result.billing.BillingResult;
import com.kerrrusha.lab234.service.money.result.open_new_card.OpenMoneyCardResult;
import com.kerrrusha.lab234.validator.AbstractValidator;
import com.kerrrusha.lab234.validator.ReplenishMoneyValidator;
import com.kerrrusha.lab234.validator.SendMoneyValidator;
import com.kerrrusha.lab234.validator.MoneyCardCreationValidator;
import com.kerrrusha.lab234.viewmodel.BillingViewModel;
import com.kerrrusha.lab234.viewmodel.MoneycardViewModel;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import static java.util.Collections.singletonList;
import static org.apache.logging.log4j.util.Strings.isBlank;

public class MoneyService {

    private static final int BILLING_STATUS_DONE_ID = 2;
    private static final int MAX_CARDS_ALLOWED = 3;

    private static final String MAX_CARDS_AMOUNT_ERROR = "Can't create money card: limit reached.";
    private static final String DATABASE_ERROR = "Something went wrong with database. Please, try again later.";
    private static final String NUMBER_FORMAT_ERROR = "Something went wrong while parsing number. Please, try again later.";

    private final Gson gson;
    private final MoneyCardDao moneyCardDao;
    private final BillingDao billingDao;
    private final BlockedMoneyAccountDao blockedMoneyAccountDao;
    private final User user;

    public MoneyService(User user) throws DBException {
        this.user = user;
        moneyCardDao = new MoneyCardDao();
        billingDao = new BillingDao();
        blockedMoneyAccountDao = new BlockedMoneyAccountDao();
        gson = GsonFactory.create();
    }

    public String getUserMoneycardsViewModelJson() throws DBException {
        return gson.toJson(buildMoneycardsViewModel());
    }

    public String getUserBillingsViewModelJson() throws DBException {
        return gson.toJson(buildBillingsViewModel());
    }

    private Collection<MoneycardViewModel> buildMoneycardsViewModel() throws DBException {
        Collection<MoneycardViewModel> viewModels = new ArrayList<>();

        Collection<MoneyCard> userMoneyCards = getUserMoneyCards();
        for (MoneyCard moneyCard : userMoneyCards) {
            MoneycardViewModel viewModel = new MoneycardViewModel();

            viewModel.setMoneyCardNumber(moneyCard.getNumber());
            viewModel.setBlocked(blockedMoneyAccountDao.entryByMoneyAccountIdExists(moneyCard.getMoneyAccountId()));
            viewModel.setBalance(moneyCard.getBalance());
            viewModel.setSecret(moneyCard.getSecret());
            viewModel.setMoneyAccountId(moneyCard.getMoneyAccountId());
            viewModel.setExpirationDate(moneyCard.getExpirationDate());
            viewModel.setOpenedDate(moneyCard.getCreatedTime().toLocalDate());
            viewModel.setMoneyAccountName(moneyCardDao.findMoneyAccountById(moneyCard.getMoneyAccountId()).getName());

            viewModels.add(viewModel);
        }

        return viewModels;
    }

    private Collection<BillingViewModel> buildBillingsViewModel() throws DBException {
        Collection<BillingViewModel> viewModels = new ArrayList<>();

        Collection<Billing> userBillins = getUserBillings();
        for (Billing billing : userBillins) {
            BillingViewModel viewModel = new BillingViewModel();
            MoneyCard fromMoneyCard = moneyCardDao.findMoneyCardById(billing.getFromMoneyCardId());
            MoneyCard toMoneyCard = moneyCardDao.findMoneyCardById(billing.getToMoneyCardId());
            BillingStatus billingStatus = billingDao.findBillingStatusById(billing.getBillingStatusId());

            viewModel.setBillingId(billing.getId());
            viewModel.setMoneyAmount(billing.getMoneyAmount());
            viewModel.setFromCardNumber(fromMoneyCard.getNumber());
            viewModel.setToCardNumber(toMoneyCard.getNumber());
            viewModel.setStatusName(billingStatus.getName());
            viewModel.setCreatedTime(billing.getCreatedTime());

            viewModels.add(viewModel);
        }

        return viewModels;
    }

    private Collection<Billing> getUserBillings() throws DBException {
        return billingDao.findBillingsByUserId(user.getId());
    }

    private Collection<MoneyCard> getUserMoneyCards() throws DBException {
        return moneyCardDao.findMoneyCardsByUserId(user.getId());
    }

    public int getUserCardsAmount() throws DBException {
        return getUserMoneyCards().size();
    }

    public int getMaxCardsAllowedAmount() {
        return MAX_CARDS_ALLOWED;
    }

    public OpenMoneyCardResult openNewMoneyCard(String name) {
        OpenMoneyCardResult result = new OpenMoneyCardResult();
        AbstractValidator validator = new MoneyCardCreationValidator(name);

        try {
            if (getUserCardsAmount() >= getMaxCardsAllowedAmount()) {
                result.setStatus(HttpStatus.SC_CONFLICT);
                result.setErrorPool(singletonList(MAX_CARDS_AMOUNT_ERROR));
                return result;
            }

            Collection<String> errorPool = validator.getErrors();
            if (!errorPool.isEmpty()) {
                result.setStatus(HttpStatus.SC_CONFLICT);
                result.setErrorPool(errorPool);
                return result;
            }

            MoneyAccount insertedMoneyAccount = createNewMoneyAccountToDb(name);

            MoneyCard insertedMoneyCard = createNewMoneyCardToDb(insertedMoneyAccount.getId());

            result.setStatus(HttpStatus.SC_OK);
            result.setMoneyCard(insertedMoneyCard);
        } catch (DBException e) {
            result.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result.setErrorPool(singletonList(DATABASE_ERROR));
            return result;
        }

        return result;
    }

    public BillingResult sendMoney(String fromMoneyAccountIdStr, String toMoneyCardNumber, String moneyAmountStr) {
        BillingResult result = new BillingResult();

        try {
            int fromMoneyAccountId = Integer.parseInt(fromMoneyAccountIdStr);
            int moneyAmount = Integer.parseInt(moneyAmountStr);
            toMoneyCardNumber = fixCardNumberIfInvalid(toMoneyCardNumber);

            AbstractValidator validator = new SendMoneyValidator(user, fromMoneyAccountId, toMoneyCardNumber, moneyAmount);
            Collection<String> errorPool = validator.getErrors();
            if (!errorPool.isEmpty()) {
                result.setStatus(HttpStatus.SC_CONFLICT);
                result.setErrorPool(errorPool);
                return result;
            }

            MoneyCard fromMoneyCard = moneyCardDao.findMoneyCardByMoneyAccountId(fromMoneyAccountId);
            MoneyCard toMoneyCard = moneyCardDao.findMoneyCardByNumber(toMoneyCardNumber);

            fromMoneyCard.takeMoney(moneyAmount);
            toMoneyCard.giveMoney(moneyAmount);

            moneyCardDao.updateMoneyCardBalance(fromMoneyCard);
            moneyCardDao.updateMoneyCardBalance(toMoneyCard);

            Billing billing = new Billing();

            billing.setMoneyAmount(moneyAmount);
            billing.setBillingStatusId(BILLING_STATUS_DONE_ID);
            billing.setFromMoneyCardId(fromMoneyCard.getId());
            billing.setToMoneyCardId(toMoneyCard.getId());

            billingDao.insertBilling(billing);

            result.setStatus(HttpStatus.SC_OK);
            result.setMoneyCard(fromMoneyCard);
        } catch (NumberFormatException e) {
            result.setStatus(HttpStatus.SC_BAD_REQUEST);
            result.setErrorPool(singletonList(NUMBER_FORMAT_ERROR));
            return result;
        } catch (DBException e) {
            result.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result.setErrorPool(singletonList(DATABASE_ERROR));
            return result;
        }

        return result;
    }

    public BillingResult replenishMoney(String toMoneyAccountIdStr, String fromMoneyCardNumber, String fromMoneyCardSecret, String moneyAmountStr) {
        BillingResult result = new BillingResult();

        try {
            int toMoneyAccountId = Integer.parseInt(toMoneyAccountIdStr);
            int moneyAmount = Integer.parseInt(moneyAmountStr);
            fromMoneyCardNumber = fixCardNumberIfInvalid(fromMoneyCardNumber);

            AbstractValidator validator = new ReplenishMoneyValidator(user, toMoneyAccountId, fromMoneyCardNumber, fromMoneyCardSecret, moneyAmount);
            Collection<String> errorPool = validator.getErrors();
            if (!errorPool.isEmpty()) {
                result.setStatus(HttpStatus.SC_CONFLICT);
                result.setErrorPool(errorPool);
                return result;
            }

            MoneyCard toMoneyCard = moneyCardDao.findMoneyCardByMoneyAccountId(toMoneyAccountId);
            MoneyCard fromMoneyCard = moneyCardDao.findMoneyCardByNumber(fromMoneyCardNumber);

            fromMoneyCard.takeMoney(moneyAmount);
            toMoneyCard.giveMoney(moneyAmount);

            moneyCardDao.updateMoneyCardBalance(fromMoneyCard);
            moneyCardDao.updateMoneyCardBalance(toMoneyCard);

            Billing billing = new Billing();

            billing.setMoneyAmount(moneyAmount);
            billing.setBillingStatusId(BILLING_STATUS_DONE_ID);
            billing.setFromMoneyCardId(fromMoneyCard.getId());
            billing.setToMoneyCardId(toMoneyCard.getId());

            billingDao.insertBilling(billing);

            result.setStatus(HttpStatus.SC_OK);
            result.setMoneyCard(toMoneyCard);
        } catch (NumberFormatException e) {
            result.setStatus(HttpStatus.SC_BAD_REQUEST);
            result.setErrorPool(singletonList(NUMBER_FORMAT_ERROR));
            return result;
        } catch (DBException e) {
            result.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result.setErrorPool(singletonList(DATABASE_ERROR));
            return result;
        }

        return result;
    }

    private String fixCardNumberIfInvalid(String toMoneyCardNumber) {
        if (isBlank(toMoneyCardNumber) ||
                toMoneyCardNumber.length() != 16 ||
                !Pattern.compile("([0-9]{4}){4}").matcher(toMoneyCardNumber).find()) {
            return toMoneyCardNumber;
        }
        return toMoneyCardNumber.substring(0, 4) + " " + toMoneyCardNumber.substring(4, 8) + " "
                + toMoneyCardNumber.substring(8, 12) + " " + toMoneyCardNumber.substring(12);
    }

    private MoneyAccount createNewMoneyAccountToDb(String name) throws DBException {
        MoneyAccount newMoneyAccount = new MoneyAccount();
        newMoneyAccount.setName(name);
        newMoneyAccount.setOwnerUserId(user.getId());
        moneyCardDao.insertMoneyAccount(newMoneyAccount);

        return moneyCardDao.findMoneyAccountByUserIdAndName(user.getId(), newMoneyAccount.getName());
    }

    private MoneyCard createNewMoneyCardToDb(int insertedMoneyAccountId) throws DBException {
        MoneyCard newMoneyCard = MoneyCardFactory.createNewMoneyCard(insertedMoneyAccountId);
        moneyCardDao.insert(newMoneyCard);

        MoneyCard insertedMoneyCard = moneyCardDao.findMoneyCardByUserIdAndSecretAndNumber(
                user.getId(),
                newMoneyCard.getSecret(),
                newMoneyCard.getNumber()
        );
        insertedMoneyCard.setNumberFromId();

        moneyCardDao.updateMoneyCardNumber(insertedMoneyCard);

        return insertedMoneyCard;
    }

    public MoneyAccount getMoneyAccountById(int fromMoneyAccountId) throws DBException {
        return moneyCardDao.findMoneyAccountById(fromMoneyAccountId);
    }

    public MoneyCard getMoneyCardByMoneyAccountId(int fromMoneyAccountId) throws DBException {
        return moneyCardDao.findMoneyCardByMoneyAccountId(fromMoneyAccountId);
    }
}
