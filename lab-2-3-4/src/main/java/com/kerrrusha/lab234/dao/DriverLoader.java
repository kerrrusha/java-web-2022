package com.kerrrusha.lab234.dao;

public class DriverLoader {

	public static void loadDriver() {
		try {
			Class.forName("org.mysql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
