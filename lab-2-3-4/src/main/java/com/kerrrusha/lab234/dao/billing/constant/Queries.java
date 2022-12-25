package com.kerrrusha.lab234.dao.billing.constant;

public class Queries {

	public static final String INSERT_BILLING = "INSERT INTO " +
			"`billing`(money_amount, from_money_card_id, to_money_card_id, billing_status_id) " +
			"values (?, ?, ?, ?)";

    public static final String FIND_BILLINS_BY_USER_ID = "SELECT * FROM `billing` " +
			"WHERE from_money_card_id in (select id from `money_card` where money_account_id in " +
			"(select id from `money_account` where owner_user_id = ?))";
    public static final String FIND_BILLING_STATUS_BY_ID = "select * from `billing_status` where id = ?";
}
