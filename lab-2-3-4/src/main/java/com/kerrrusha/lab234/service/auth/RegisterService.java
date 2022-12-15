package com.kerrrusha.lab234.service.auth;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.user.UserDao;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.service.auth.result.AuthResult;
import com.kerrrusha.lab234.validator.AbstractValidator;
import com.kerrrusha.lab234.validator.auth.RegisterValidator;
import org.apache.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;

public class RegisterService {

	private static final String DATABASE_ERROR = "An error occurred on the server while creating a new user. We will definitely fix this, but for now, please try again.";
	private static final int OK_STATUS = HttpStatus.SC_OK;
	private static final int ERROR_STATUS = HttpStatus.SC_CONFLICT;

	private final String firstName;
	private final String lastName;
	private final String phone;
	private final String password;
	private final String passwordRepeat;
	private final int roleId;

	public RegisterService(String firstName, String lastName, String phone, String password, String passwordRepeat, int roleId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.password = password;
		this.passwordRepeat = passwordRepeat;
		this.roleId = roleId;
	}

	public AuthResult processRegister(HttpServletRequest request) {
		AuthResult result = new AuthResult();
		AbstractValidator validator = new RegisterValidator(firstName, lastName, phone, password, passwordRepeat);

		Collection<String> errorPool = validator.getErrors();
		if (validator.onlyErrorAlreadyExists()) {
			User user;
			try {
				user = new UserDao().findOneByPhone(phone);
				new AuthService(request).authenticate(user);
			} catch (DBException e) {
				result.setErrorPool(Collections.singleton(e.getMessage()));
				result.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			result.setUser(user);
			result.setStatus(OK_STATUS);
			return result;
		}
		if (!errorPool.isEmpty()) {
			result.setErrorPool(errorPool);
			result.setStatus(ERROR_STATUS);
			return result;
		}

		User newUser;
		try {
			newUser = addUserToDatabase();
		} catch (DBException e) {
			errorPool.add(DATABASE_ERROR);
			result.setErrorPool(errorPool);
			result.setStatus(ERROR_STATUS);
			return result;
		}

		try {
			new AuthService(request).authenticate(newUser);
		} catch (DBException e) {
			result.setErrorPool(Collections.singleton(e.getMessage()));
			result.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			return result;
		}

		result.setUser(newUser);
		result.setStatus(OK_STATUS);
		return result;
	}

	private User addUserToDatabase() throws DBException {
		UserDao dao = new UserDao();
		User user = new User();

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setPhone(phone);
		user.setPassword(password);
		user.setRoleId(roleId);

		dao.insert(user);
		return user;
	}
}
