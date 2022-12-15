package com.kerrrusha.lab234.dao.user.constant;

public class Queries {

	public static final String SELECT_ALL_USERS = "SELECT * FROM `user`";

	public static final String INSERT_USER = "INSERT INTO `user`(first_name, last_name, phone, password, role_id) VALUES (?, ?, ?, ?, ?)";

	public static final String FIND_USER_BY_ID = "SELECT * FROM `user` WHERE id = ?";
	public static final String FIND_USER_BY_PHONE = "SELECT * FROM `user` WHERE phone = ?";

	public static final String DELETE_USER_BY_ID = "DELETE FROM `user` where id = ?";
	public static final String DELETE_USER_BY_PHONE = "DELETE FROM `user` where phone = ?";
}
