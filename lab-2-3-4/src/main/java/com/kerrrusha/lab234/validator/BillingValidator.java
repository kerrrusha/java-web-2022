package com.kerrrusha.lab234.validator;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.moneycard.MoneyCardDao;
import com.kerrrusha.lab234.model.User;

import java.util.Optional;

import static com.kerrrusha.lab234.util.ValidatorUtil.checkIfFieldIsNull;

public class BillingValidator extends AbstractValidator {

    private static final String USER_IS_NULL = "Something with current authorization session. We will definitely fix this, but for now, please try again.";
    private static final String RESTRICTED_MONEY_ACCOUNT = "Such money account does not exists or don't belongs to you.";
    private static final String INVALID_MONEY_ACCOUNT_ID = "Money account id must be greater than 0.";

    private final User user;
    private final int fromMoneyAccountId;

    public BillingValidator(User user, int fromMoneyAccountId) {
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
            return new MoneyCardDao().findMoneyAccountById(fromMoneyAccountId).getOwnerUserId() == user.getId()
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
