package com.kerrrusha.lab234.dao.moneycard.constant;

public class Queries {

	public static final String FIND_MONEY_CARDS_BY_USER_ID = "SELECT * FROM `money_card` " +
			"WHERE money_account_id in (select id from `money_account` where owner_user_id = ?)";
	public static final String FIND_MONEY_ACCOUNT_BY_USER_ID_AND_NAME = "SELECT * FROM `money_account` " +
			"WHERE owner_user_id = (select id from `user` where id = ?) and name = ? order by id desc";
	public static final String FIND_MONEY_CARD_BY_USER_ID_AND_SECRET_AND_NUMBER = "SELECT * FROM `money_card` " +
			"WHERE money_account_id in (select id FROM `money_account` " +
			"WHERE owner_user_id = (select id from `user` where id = ?)) and secret = ? and number = ?";
	public static final String FIND_MONEY_ACCOUNT_BY_ID = "SELECT * FROM `money_account` WHERE id = ?";
	public static final String FIND_MONEY_CARD_BY_MONEY_ACCOUNT_ID = "SELECT * FROM `money_card` WHERE money_account_id = ?";
	public static final String FIND_MONEY_CARDS_BY_NUMBER = "SELECT * FROM `money_card` WHERE number = ?";
	public static final String FIND_MONEY_CARDS_BY_ID = "SELECT * FROM `money_card` WHERE id = ?";

	public static final String INSERT_MONEY_CARD = "INSERT INTO " +
			"`money_card`(money_account_id, number, expiration_date, secret, balance) " +
			"values (?, ?, ?, ?, ?)";
	public static final String INSERT_MONEY_ACCOUNT = "INSERT INTO " +
			"`money_account`(name, owner_user_id) " +
			"values (?, ?)";

	public static final String UPDATE_MONEY_CARD_NUMBER = "UPDATE `money_card` " +
			"set number = ? " +
			"where id = ?";
	public static final String UPDATE_MONEY_CARD_BALANCE = "UPDATE `money_card` " +
			"set balance = ? " +
			"where id = ?";
}
