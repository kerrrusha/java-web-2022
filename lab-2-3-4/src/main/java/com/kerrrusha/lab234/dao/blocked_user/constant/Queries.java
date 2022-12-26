package com.kerrrusha.lab234.dao.blocked_user.constant;

public class Queries {

    public static final String FIND_BY_BLOCKED_USER_ID = "select * from `blocked_user` " +
			"where blocked_user_id = ?";
    public static final String INSERT_BLOCKED_USER = "INSERT INTO `blocked_user`(blocked_user_id, blocked_by_user_id)" +
            " VALUES (?, ?)";
}
