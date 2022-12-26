package com.kerrrusha.lab234.validator;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.moneycard.MoneyCardDao;
import com.kerrrusha.lab234.model.MoneyCard;
import com.kerrrusha.lab234.model.User;

import java.util.Optional;

import static com.kerrrusha.lab234.util.ValidatorUtil.checkIfStringIsBlank;

public class ReplenishMoneyValidator extends MoneyAccountValidator {

    private static final String MONEY_CARD_DOES_NOT_EXISTS = "Such money card does not exists.";
    private static final String INVALID_MONEY_AMOUNT = "Money amount cannot be less that 0.";
    private static final String NOT_ENOUGH_MONEY = "There are not enough funds on senders money account.";
    private static final String MONEY_CARD_IS_NULL = "Something is wrong with sending money card number. We will definitely fix this, but for now, please try again.";
    private static final String MONEY_SECRET_IS_NULL = "Something is wrong with sending money card secret. We will definitely fix this, but for now, please try again.";
    private static final String SECRET_IS_INVALID = "Money card secret is invalid.";

    private final String fromMoneyCardNumber;
    private final String fromMoneyCardSecret;
    private final int moneyAmount;

    public ReplenishMoneyValidator(User user, int toMoneyAccountId, String fromMoneyCardNumber, String fromMoneyCardSecret, int moneyAmount) {
        super(user, toMoneyAccountId);
        this.fromMoneyCardNumber = fromMoneyCardNumber;
        this.fromMoneyCardSecret = fromMoneyCardSecret;
        this.moneyAmount = moneyAmount;
    }

    @Override
    protected void validate() {
        super.validate();
        addPossibleError(checkIfStringIsBlank(fromMoneyCardNumber, MONEY_CARD_IS_NULL));
        addPossibleError(checkIfStringIsBlank(fromMoneyCardSecret, MONEY_SECRET_IS_NULL));
        addPossibleError(validateFromMoneyCard());
        addPossibleError(validateMoneyAmount());
        addPossibleError(validateMoneyAmountIsEnough());
    }

    private Optional<String> validateFromMoneyCard() {
        MoneyCard fromMoneyCard;
        try {
            fromMoneyCard = new MoneyCardDao().findMoneyCardByNumber(fromMoneyCardNumber);
            if (fromMoneyCard == null) {
                return Optional.of(MONEY_CARD_DOES_NOT_EXISTS);
            }
            return fromMoneyCard.getSecret().equals(fromMoneyCardSecret)
                    ? Optional.empty()
                    : Optional.of(SECRET_IS_INVALID);
        } catch (DBException e) {
            return Optional.of(DATABASE_ERROR);
        }
    }

    private Optional<String> validateMoneyAmount() {
        return moneyAmount < 0 ? Optional.of(INVALID_MONEY_AMOUNT) : Optional.empty();
    }

    private Optional<String> validateMoneyAmountIsEnough() {
        try {
            return new MoneyCardDao().findMoneyCardByNumber(fromMoneyCardNumber).getBalance() < moneyAmount
                    ? Optional.of(NOT_ENOUGH_MONEY)
                    : Optional.empty();
        } catch (DBException e) {
            return Optional.of(DATABASE_ERROR);
        }
    }

}
