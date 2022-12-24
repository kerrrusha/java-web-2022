package com.kerrrusha.lab234.service.moneycard;

import com.google.gson.Gson;
import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.moneycard.MoneyCardDao;
import com.kerrrusha.lab234.factory.GsonFactory;
import com.kerrrusha.lab234.factory.MoneyCardFactory;
import com.kerrrusha.lab234.model.MoneyAccount;
import com.kerrrusha.lab234.model.MoneyCard;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.validator.AbstractValidator;
import com.kerrrusha.lab234.validator.MoneyCardValidator;
import com.kerrrusha.lab234.viewmodel.MoneycardViewModel;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.singletonList;

public class MoneyCardService {

    private static final int maxActiveCardsAllowedAmount = 3;
    private static final String MAX_CARDS_AMOUNT_ERROR = "Can't create money card: limit reached.";
    private static final String DATABASE_ERROR = "Something went wrong with database. Please, try again later.";

    private final Gson gson;
    private final MoneyCardDao moneyCardDao;
    private final User user;

    public MoneyCardService(User user) throws DBException {
        this.user = user;
        moneyCardDao = new MoneyCardDao();
        gson = GsonFactory.create();
    }

    public String getUserMoneycardsViewModelJson() throws DBException {
        return gson.toJson(buildMoneycardsViewModel());
    }

    private Collection<MoneycardViewModel> buildMoneycardsViewModel() throws DBException {
        Collection<MoneycardViewModel> viewModels = new ArrayList<>();

        Collection<MoneyCard> userMoneyCards = getUserMoneyCards();
        for (MoneyCard moneyCard : userMoneyCards) {
            MoneycardViewModel viewModel = new MoneycardViewModel();

            viewModel.setMoneyCardNumber(moneyCard.getNumber());
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

    private Collection<MoneyCard> getUserMoneyCards() throws DBException {
        return moneyCardDao.findByUserId(user.getId());
    }

    public int getUserCardsAmount() throws DBException {
        return getUserMoneyCards().size();
    }

    public int getMaxCardsAllowedAmount() {
        return maxActiveCardsAllowedAmount;
    }

    public OpenMoneyCardResult openNewMoneyCard(String name) {
        OpenMoneyCardResult result = new OpenMoneyCardResult();
        AbstractValidator validator = new MoneyCardValidator(name);

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

        MoneyCard insertedMoneyCard = moneyCardDao.findOneByUserIdAndSecretAndNumber(
                user.getId(),
                newMoneyCard.getSecret(),
                newMoneyCard.getNumber()
        );
        insertedMoneyCard.setNumberFromId();

        moneyCardDao.updateNumber(insertedMoneyCard);

        return insertedMoneyCard;
    }
}
