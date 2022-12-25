package com.kerrrusha.lab234.service.auth;

import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.service.AbstractResult;

public class AuthResult extends AbstractResult {

	private transient User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
