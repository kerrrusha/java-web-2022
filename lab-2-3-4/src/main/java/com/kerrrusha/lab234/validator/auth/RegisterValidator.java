package com.kerrrusha.lab234.validator.auth;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.user.UserDao;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.validator.AbstractValidator;

import java.util.Collection;
import java.util.Optional;

import static com.kerrrusha.lab234.util.ValidatorUtil.checkIfFieldIsNull;
import static java.lang.Character.isDigit;

public class RegisterValidator extends AbstractValidator {

	public static final String PHONE_ALREADY_EXISTS = "Such phone already exists.";
	private static final String INCORRECT_FIRST_NAME = "First name must be 3 symbols at least.";
	private static final String INCORRECT_LAST_NAME = "Last name must be 3 symbols at least.";
	private static final String INCORRECT_PHONE = "Phone must be in format like '+380123456789'.";
	private static final String INCORRECT_PASSWORD = "Password must be at least 3 characters long and must not contain spaces.";
	private static final String PASSWORDS_DO_NOT_MATCHES = "Passwords don't match.";
	private static final String IS_NULL_TEMPLATE = "Something is wrong while submitting %s to the server. We will definitely fix this, but for now, please try again.";
	private static final String FIRST_NAME_IS_NULL = String.format(IS_NULL_TEMPLATE, "first name");
	private static final String LAST_NAME_IS_NULL = String.format(IS_NULL_TEMPLATE, "last name");
	private static final String PHONE_IS_NULL = String.format(IS_NULL_TEMPLATE, "phone");
	private static final String PASSWORD_IS_NULL = String.format(IS_NULL_TEMPLATE, "password");
	private static final String PASSWORD_REPEAT_IS_NULL = String.format(IS_NULL_TEMPLATE, "repeat of the password");

	private final String firstName;
	private final String lastName;
	private final String phone;
	private final String password;
	private final String passwordRepeat;

	public RegisterValidator(String firstName, String lastName, String phone, String password, String passwordRepeat) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.password = password;
		this.passwordRepeat = passwordRepeat;
	}

	@Override
	protected void validate() {
		addPossibleError(checkIfFieldIsNull(firstName, FIRST_NAME_IS_NULL));
		addPossibleError(checkIfFieldIsNull(lastName, LAST_NAME_IS_NULL));
		addPossibleError(checkIfFieldIsNull(phone, PHONE_IS_NULL));
		addPossibleError(checkIfFieldIsNull(password, PASSWORD_IS_NULL));
		addPossibleError(checkIfFieldIsNull(passwordRepeat, PASSWORD_REPEAT_IS_NULL));
		addPossibleError(validateFirstName());
		addPossibleError(validateLastName());
		addPossibleError(validatePhone());
		addPossibleError(validatePasswords());
		addPossibleError(validateIfExists());
	}

	private Optional<String> validateFirstName() {
		return firstName.trim().length() < 3 ? Optional.of(INCORRECT_FIRST_NAME) : Optional.empty();
 	}

	private Optional<String> validateLastName() {
		return lastName.trim().length() < 3 ? Optional.of(INCORRECT_LAST_NAME) : Optional.empty();
	}

	private Optional<String> validatePhone() {
		if (phone.length() != 13) {
			return Optional.of(INCORRECT_PHONE);
		}
		if (phone.charAt(0) != '+') {
			return Optional.of(INCORRECT_PHONE);
		}
		for (int i = 1; i < phone.length(); i++) {
			if (!isDigit(phone.charAt(i))) {
				return Optional.of(INCORRECT_PHONE);
			}
		}
		return Optional.empty();
	}

	private Optional<String> validatePasswords() {
		if ((password.length() < 3 || password.contains(" ") || passwordRepeat.length() < 3 || passwordRepeat.contains(" "))) {
			return Optional.of(INCORRECT_PASSWORD);
		}
		return password.equals(passwordRepeat) ? Optional.empty() : Optional.of(PASSWORDS_DO_NOT_MATCHES);
	}

	private Optional<String> validateIfExists() {
		User user;

		try {
			user = new UserDao().findOneByPhone(phone);
		} catch (DBException e) {
			return Optional.of(DATABASE_ERROR);
		}

		return user == null ? Optional.empty() : Optional.of(PHONE_ALREADY_EXISTS);
	}
}
