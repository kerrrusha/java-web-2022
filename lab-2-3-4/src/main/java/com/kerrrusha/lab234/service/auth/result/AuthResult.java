package com.kerrrusha.lab234.service.auth.result;

import com.kerrrusha.lab234.model.User;

import java.util.Collection;

public class AuthResult {

	private Collection<String> errorPool;
	private int status;
	private transient User user;

	public Collection<String> getErrorPool() {
		return errorPool;
	}

	public void setErrorPool(Collection<String> errorPool) {
		this.errorPool = errorPool;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isOK() {
		return status == 200;
	}
}
