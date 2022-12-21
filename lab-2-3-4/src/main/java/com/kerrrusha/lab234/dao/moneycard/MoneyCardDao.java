package com.kerrrusha.lab234.dao.moneycard;

import com.kerrrusha.lab234.dao.AbstractDao;
import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.moneycard.constant.Fields;
import com.kerrrusha.lab234.dao.moneycard.constant.Queries;
import com.kerrrusha.lab234.model.MoneyCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import static com.kerrrusha.lab234.util.SqlUtil.mapToLocalDate;
import static com.kerrrusha.lab234.util.SqlUtil.mapToLocalDateTime;

public class MoneyCardDao extends AbstractDao {

	public MoneyCardDao() throws DBException {}

	public Collection<MoneyCard> findByUserId(int userId) throws DBException {
		Collection<MoneyCard> entities = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(FULL_URL);
		     PreparedStatement stmt = con.prepareStatement(Queries.FIND_MONEY_CARDS_BY_USER_ID)) {
			stmt.setInt(1, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					entities.add(mapToEntity(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
		return entities;
	}

	private MoneyCard mapToEntity(ResultSet rs) throws SQLException {
		MoneyCard entity = new MoneyCard();

		entity.setId(rs.getInt(Fields.MONEY_CARD_ID));
		entity.setMoneyAccountId(rs.getInt(Fields.MONEY_CARD_MONEY_ACCOUNT_ID));
		entity.setBalance(rs.getInt(Fields.MONEY_CARD_BALANCE));
		entity.setNumber(rs.getString(Fields.MONEY_CARD_NUMBER));
		entity.setSecret(rs.getString(Fields.MONEY_CARD_SECRET));
		entity.setExpirationDate(mapToLocalDate(rs.getTimestamp(Fields.MONEY_CARD_EXPIRATION_DATE)));
		entity.setCreatedTime(mapToLocalDateTime(rs.getTimestamp(Fields.MONEY_CARD_CREATED_TIME)));
		entity.setUpdatedTime(mapToLocalDateTime(rs.getTimestamp(Fields.MONEY_CARD_UPDATED_TIME)));

		return entity;
	}
}
