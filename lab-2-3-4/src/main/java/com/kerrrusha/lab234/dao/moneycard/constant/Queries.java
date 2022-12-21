package com.kerrrusha.lab234.dao.moneycard.constant;

public class Queries {

	public static final String FIND_MONEY_CARDS_BY_USER_ID = "SELECT * FROM `money_card` " +
			"WHERE money_account_id = (select id from `money_account` where owner_user_id = ?)";
}
