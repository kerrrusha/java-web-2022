package com.kerrrusha.lab234.validator;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.user.UserDao;
import com.kerrrusha.lab234.model.User;

import java.util.List;
import java.util.Optional;

import static com.kerrrusha.lab234.util.ValidatorUtil.checkIfFieldIsNull;
import static java.util.stream.Collectors.toList;

public class BlockUserValidator extends AbstractValidator {

    private static final String USER_IS_NULL = "Something is wrong with current authorization session. We will definitely fix this, but for now, please try again.";
    private static final String INVALID_USER_ID = "User id must be greater than 0.";
    private static final String USER_NOT_EXISTS = "Such user does not exists.";
    private static final String RESTRICTED_USER = "You have no access to such client account.";

    private final User blockedByUser;
    private final int userIdToBeBlocked;

    public BlockUserValidator(User blockedByUser, int userIdToBeBlocked) {
        this.blockedByUser = blockedByUser;
        this.userIdToBeBlocked = userIdToBeBlocked;
    }

    public boolean onlyErrorRestrictedUser() {
        List<String> errors = possibleErrors.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
        return errors.size() == 1 && errors.get(0).equals(BlockUserValidator.RESTRICTED_USER);
    }

    @Override
    protected void validate() {
        addPossibleError(checkIfFieldIsNull(blockedByUser, USER_IS_NULL));
        addPossibleError(validateThatUserIdIsGreaterThanZero());
        addPossibleError(validateThatUserExists());
        addPossibleError(validateThatAccountBelongsToUser());
    }

    private Optional<String> validateThatUserIdIsGreaterThanZero() {
        return userIdToBeBlocked > 0 ? Optional.empty() : Optional.of(INVALID_USER_ID);
    }

    private Optional<String> validateThatUserExists() {
        try {
            return new UserDao().findOneById(userIdToBeBlocked) != null
                    ? Optional.empty()
                    : Optional.of(USER_NOT_EXISTS);
        } catch (DBException e) {
            return Optional.of(DATABASE_ERROR);
        }
    }

    private Optional<String> validateThatAccountBelongsToUser() {
        return blockedByUser.getId() == userIdToBeBlocked
                ? Optional.empty()
                : Optional.of(RESTRICTED_USER);
    }
}
