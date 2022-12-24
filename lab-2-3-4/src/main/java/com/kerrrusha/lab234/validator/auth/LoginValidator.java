package com.kerrrusha.lab234.validator.auth;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.user.UserDao;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.validator.AbstractValidator;

import java.util.Optional;

import static com.kerrrusha.lab234.util.ValidatorUtil.checkIfFieldIsNull;

public class LoginValidator extends AbstractValidator {

	private static final String INVALID_PHONE = "Such login does not exists.";
	private static final String INVALID_PASSWORD = "Password is invalid.";
	private static final String PHONE_IS_NULL = "Something is wrong while submitting login to the server. We will definitely fix this, but for now, please try again.";
	private static final String PASSWORD_IS_NULL = "Something is wrong while submitting password to the server. We will definitely fix this, but for now, please try again.";

	private final String phone;
	private final String password;

	public LoginValidator(String phone, String password) {
		this.phone = phone;
		this.password = password;
	}

	@Override
	protected void validate() {
		addPossibleError(checkIfFieldIsNull(phone, PHONE_IS_NULL));
		addPossibleError(checkIfFieldIsNull(password, PASSWORD_IS_NULL));
		addPossibleError(validateUser());
	}

	private Optional<String> validateUser() {
		User user;

		try {
			user = new UserDao().findOneByPhone(phone);
		} catch (DBException e) {
			return Optional.of(DATABASE_ERROR);
		}

		Optional<String> possibleError = validatePhone(user);

		return possibleError.isPresent() ? possibleError : validatePassword(user);
	}

	private Optional<String> validatePhone(User user) {
		return user == null ? Optional.of(INVALID_PHONE) : Optional.empty();
	}

	private Optional<String> validatePassword(User user) {
		return user.getPassword().equals(password) ? Optional.empty() : Optional.of(INVALID_PASSWORD);
	}
}
