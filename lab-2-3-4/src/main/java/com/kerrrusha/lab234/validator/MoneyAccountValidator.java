package com.kerrrusha.lab234.validator;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.moneycard.MoneyCardDao;
import com.kerrrusha.lab234.model.MoneyAccount;
import com.kerrrusha.lab234.model.User;

import java.util.Optional;

import static com.kerrrusha.lab234.util.ValidatorUtil.checkIfFieldIsNull;
import static java.util.Objects.isNull;

public class MoneyAccountValidator extends AbstractValidator {

    private static final String USER_IS_NULL = "Something is wrong with current authorization session. We will definitely fix this, but for now, please try again.";
    private static final String RESTRICTED_MONEY_ACCOUNT = "Such money account does not belongs to you.";
    private static final String INVALID_MONEY_ACCOUNT_ID = "Money account id must be greater than 0.";
    private static final String MONEY_ACCOUNT_DOES_NOT_EXISTS = "Such money account does not exists.";

    private final User user;
    protected final int fromMoneyAccountId;

    public MoneyAccountValidator(User user, int fromMoneyAccountId) {
        this.user = user;
        this.fromMoneyAccountId = fromMoneyAccountId;
    }

    @Override
    protected void validate() {
        addPossibleError(checkIfFieldIsNull(user, USER_IS_NULL));
        addPossibleError(validateFromMoneyAccountId());
        addPossibleError(validateThatMoneyAccountIdBelongsToUser());
    }

    private Optional<String> validateThatMoneyAccountIdBelongsToUser() {
        try {
            MoneyAccount moneyAccount = new MoneyCardDao().findMoneyAccountById(fromMoneyAccountId);
            if (isNull(moneyAccount)) {
                return Optional.of(MONEY_ACCOUNT_DOES_NOT_EXISTS);
            }
            return moneyAccount.getOwnerUserId() == user.getId()
                    ? Optional.empty()
                    : Optional.of(RESTRICTED_MONEY_ACCOUNT);
        } catch (DBException e) {
            return Optional.of(DATABASE_ERROR);
        }
    }

    private Optional<String> validateFromMoneyAccountId() {
        return fromMoneyAccountId <= 0 ? Optional.of(INVALID_MONEY_ACCOUNT_ID) : Optional.empty();
    }
}
