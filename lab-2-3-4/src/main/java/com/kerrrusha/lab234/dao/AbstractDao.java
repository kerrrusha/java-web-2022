package com.kerrrusha.lab234.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.kerrrusha.lab234.dao.DriverLoader.loadDriver;

public class AbstractDao {

	protected String FULL_URL;

	protected AbstractDao() throws DBException {
		Properties properties = new Properties();
		try (InputStream is = getClass().getResourceAsStream("/application.properties")) {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
		FULL_URL = properties.getProperty("connection.url");

		loadDriver();
	}
}
