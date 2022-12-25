package com.kerrrusha.lab234.validator;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.moneycard.MoneyCardDao;
import com.kerrrusha.lab234.model.User;

import java.util.Optional;

import static com.kerrrusha.lab234.util.ValidatorUtil.checkIfStringIsBlank;

public class BillingValidator extends MoneyAccountValidator {

    private static final String MONEY_CARD_DOES_NOT_EXISTS = "Such money card does not exists.";
    private static final String INVALID_MONEY_AMOUNT = "Money amount cannot be less that 0.";
    private static final String NOT_ENOUGH_MONEY = "There are not enough funds on your account.";
    private static final String MONEY_CARD_IS_NULL = "Something is wrong with sending money card number. We will definitely fix this, but for now, please try again.";

    private final String toMoneyCardNumber;
    private final int moneyAmount;

    public BillingValidator(User user, int fromMoneyAccountId, String toMoneyCardNumber, int moneyAmount) {
        super(user, fromMoneyAccountId);
        this.toMoneyCardNumber = toMoneyCardNumber;
        this.moneyAmount = moneyAmount;
    }

    @Override
    protected void validate() {
        super.validate();
        addPossibleError(checkIfStringIsBlank(toMoneyCardNumber, MONEY_CARD_IS_NULL));
        addPossibleError(validateToMoneyCard());
        addPossibleError(validateMoneyAmount());
        addPossibleError(validateMoneyAmountIsEnough());
    }

    private Optional<String> validateMoneyAmountIsEnough() {
        try {
            return new MoneyCardDao().findMoneyCardByMoneyAccountId(fromMoneyAccountId).getBalance() < moneyAmount
                    ? Optional.of(NOT_ENOUGH_MONEY)
                    : Optional.empty();
        } catch (DBException e) {
            return Optional.of(DATABASE_ERROR);
        }
    }

    private Optional<String> validateMoneyAmount() {
        return moneyAmount < 0 ? Optional.of(INVALID_MONEY_AMOUNT) : Optional.empty();
    }

    private Optional<String> validateToMoneyCard() {
        try {
            return new MoneyCardDao().findMoneyCardByNumber(toMoneyCardNumber) == null
                    ? Optional.of(MONEY_CARD_DOES_NOT_EXISTS)
                    : Optional.empty();
        } catch (DBException e) {
            return Optional.of(DATABASE_ERROR);
        }
    }
}
