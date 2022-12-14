package com.kerrrusha.lab234.validator;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.blocked_money_account.BlockedMoneyAccountDao;
import com.kerrrusha.lab234.dao.moneycard.MoneyCardDao;
import com.kerrrusha.lab234.model.MoneyAccount;
import com.kerrrusha.lab234.model.User;

import java.util.List;
import java.util.Optional;

import static com.kerrrusha.lab234.util.ValidatorUtil.checkIfFieldIsNull;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

public class MoneyAccountValidator extends AbstractValidator {

    private static final String USER_IS_NULL = "Something is wrong with current authorization session. We will definitely fix this, but for now, please try again.";
    private static final String RESTRICTED_MONEY_ACCOUNT = "Such money account does not belongs to you.";
    private static final String INVALID_MONEY_ACCOUNT_ID = "Money account id must be greater than 0.";
    private static final String MONEY_ACCOUNT_DOES_NOT_EXISTS = "Such money account does not exists.";
    private static final String MONEY_ACCOUNT_IS_BLOCKED = "This money account is blocked, so you can't perform money transfer.";

    private final User user;
    protected final int fromMoneyAccountId;

    public MoneyAccountValidator(User user, int fromMoneyAccountId) {
        this.user = user;
        this.fromMoneyAccountId = fromMoneyAccountId;
    }

    public boolean onlyErrorRestrictedMoneyAccount() {
        List<String> errors = possibleErrors.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
        return errors.size() == 1 && errors.get(0).equals(MoneyAccountValidator.RESTRICTED_MONEY_ACCOUNT);
    }

    @Override
    protected void validate() {
        addPossibleError(checkIfFieldIsNull(user, USER_IS_NULL));
        addPossibleError(validateFromMoneyAccountIdNumberIsGreaterThanZero());
        addPossibleError(validateThatMoneyAccountIsNotBlocked());
        addPossibleError(validateThatMoneyAccountIdBelongsToUser());
    }

    private Optional<String> validateThatMoneyAccountIsNotBlocked() {
        try {
            return new BlockedMoneyAccountDao().entryByMoneyAccountIdExists(fromMoneyAccountId)
                    ? Optional.of(MONEY_ACCOUNT_IS_BLOCKED)
                    : Optional.empty();
        } catch (DBException e) {
            return Optional.of(DATABASE_ERROR);
        }
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

    private Optional<String> validateFromMoneyAccountIdNumberIsGreaterThanZero() {
        return fromMoneyAccountId <= 0 ? Optional.of(INVALID_MONEY_ACCOUNT_ID) : Optional.empty();
    }
}
