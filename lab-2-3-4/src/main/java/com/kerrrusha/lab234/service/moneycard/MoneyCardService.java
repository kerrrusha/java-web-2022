package com.kerrrusha.lab234.service.moneycard;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.moneycard.MoneyCardDao;
import com.kerrrusha.lab234.factory.MoneyCardFactory;
import com.kerrrusha.lab234.model.MoneyAccount;
import com.kerrrusha.lab234.model.MoneyCard;
import com.kerrrusha.lab234.model.User;
import org.apache.http.HttpStatus;

import java.util.Collection;

import static java.util.Collections.singletonList;

public class MoneyCardService {

    private static final int maxActiveCardsAllowedAmount = 3;
    private static final String MAX_CARDS_AMOUNT_ERROR = "Can't create money card: limit reached.";
    private static final String DATABASE_ERROR = "Something went wrong with database. Please, try again later.";
    private static final String INVALID_NAME_ERROR = "Money account name should be 3 symbols length at least.";

    private final MoneyCardDao moneyCardDao;
    private final User user;

    public MoneyCardService(User user) throws DBException {
        moneyCardDao = new MoneyCardDao();
        this.user = user;
    }

    public Collection<MoneyCard> getUserMoneyCards() throws DBException {
        return moneyCardDao.findByUserId(user.getId());
    }

    public int getUserActiveCardsAmount() throws DBException {
        return (int) getUserMoneyCards().stream()
                .filter(MoneyCard::isActive)
                .count();
    }

    public int getMaxActiveCardsAllowedAmount() {
        return maxActiveCardsAllowedAmount;
    }

    public OpenMoneyCardResult openNewMoneyCard(String name) {
        OpenMoneyCardResult result = new OpenMoneyCardResult();

        try {
            if (getUserActiveCardsAmount() >= getMaxActiveCardsAllowedAmount()) {
                result.setStatus(HttpStatus.SC_CONFLICT);
                result.setErrorPool(singletonList(MAX_CARDS_AMOUNT_ERROR));
                return result;
            }

            if (name == null || name.trim().length() < 3) {
                result.setStatus(HttpStatus.SC_CONFLICT);
                result.setErrorPool(singletonList(INVALID_NAME_ERROR));
                return result;
            }

            MoneyAccount newMoneyAccount = new MoneyAccount();
            newMoneyAccount.setName(name);
            newMoneyAccount.setOwnerUserId(user.getId());
            moneyCardDao.insertMoneyAccount(newMoneyAccount);

            MoneyAccount insertedMoneyAccount = moneyCardDao.findMoneyAccountByUserIdAndName(user.getId(), newMoneyAccount.getName());
            MoneyCard newMoneyCard = MoneyCardFactory.createNewMoneyCard(insertedMoneyAccount.getId());
            moneyCardDao.insert(newMoneyCard);

            MoneyCard insertedMoneyCard = moneyCardDao.findOneByUserIdAndSecretAndNumber(
                            user.getId(),
                            newMoneyCard.getSecret(),
                            newMoneyCard.getNumber()
                    );
            MoneyCard resultMoneyCard = insertedMoneyCard.createWithNumberFromId();

            moneyCardDao.updateNumber(resultMoneyCard);

            result.setStatus(HttpStatus.SC_OK);
            result.setMoneyCard(resultMoneyCard);
        } catch (DBException e) {
            result.setStatus(HttpStatus.SC_CONFLICT);
            result.setErrorPool(singletonList(DATABASE_ERROR));
            return result;
        }

        return result;
    }
}
