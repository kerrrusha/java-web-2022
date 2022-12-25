package com.kerrrusha.lab234.dao.billing.constant;

public class Queries {

	public static final String INSERT_BILLING = "INSERT INTO " +
			"`billing`(money_amount, from_money_card_id, to_money_card_id, billing_status_id) " +
			"values (?, ?, ?, ?)";
}
