package com.kerrrusha.lab234.service.auth;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.user.UserDao;
import com.kerrrusha.lab234.model.User;

import javax.servlet.http.HttpServletRequest;

public class AuthService {

	private final HttpServletRequest request;

	public AuthService(HttpServletRequest request) {
		this.request = request;
	}

	public void authenticate(User user) throws DBException {
		request.getSession().setAttribute("user", new UserDao().findOneByPhone(user.getPhone()));
	}

	public void signout() {
		request.getSession().invalidate();
	}
}
