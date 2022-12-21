package com.kerrrusha.lab234.service.auth;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.user.UserDao;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.validator.AbstractValidator;
import com.kerrrusha.lab234.validator.auth.LoginValidator;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;

public class LoginService {

	private static final Logger logger = Logger.getLogger(LoginService.class);

	private static final String DATABASE_ERROR = "An error occurred on the server while creating a new user. We will definitely fix this, but for now, please try again.";
	private static final int OK_STATUS = HttpStatus.SC_OK;
	private static final int ERROR_STATUS = HttpStatus.SC_CONFLICT;

	private final String phone;
	private final String password;

	public LoginService(String login, String password) {
		this.phone = login;
		this.password = password;
	}

	public AuthResult processLogin(HttpServletRequest request) {
		AuthResult result = new AuthResult();
		AbstractValidator validator = new LoginValidator(phone, password);

		Collection<String> errorPool = validator.getErrors();
		if (!errorPool.isEmpty()) {
			result.setErrorPool(errorPool);
			result.setStatus(ERROR_STATUS);
			return result;
		}

		User user;
		try {
			user = new UserDao().findOneByPhone(phone);
		} catch (DBException e) {
			errorPool.add(DATABASE_ERROR);
			result.setErrorPool(errorPool);
			result.setStatus(ERROR_STATUS);
			return result;
		}

		try {
			new AuthService(request).authenticate(user);
		} catch (DBException e) {
			result.setErrorPool(Collections.singleton(e.getMessage()));
			result.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			return result;
		}

		result.setStatus(OK_STATUS);
		result.setUser(getUser());
		return result;
	}

	private User getUser() {
		try {
			return new UserDao().findOneByPhone(phone);
		} catch (DBException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}
}
