package com.kerrrusha.lab234.dao;

import java.sql.SQLException;

public class DBException extends SQLException {

	private final String message;

	public DBException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
