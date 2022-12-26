package com.kerrrusha.lab234.dao.blocked_money_account.constant;

public class Queries {

    public static final String FIND_BY_BLOCKED_MONEY_ACCOUNT_ID = "select * from `blocked_money_account` " +
			"where blocked_money_account_id = ?";
    public static final String INSERT_BLOCKED_MONEY_ACCOUNT = "INSERT INTO `blocked_money_account`(blocked_money_account_id, blocked_by_user_id)" +
            " VALUES (?, ?)";
}
