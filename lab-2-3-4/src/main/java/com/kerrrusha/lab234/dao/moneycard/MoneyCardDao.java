package com.kerrrusha.lab234.dao.moneycard;

import com.kerrrusha.lab234.dao.AbstractDao;
import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.moneycard.constant.Fields;
import com.kerrrusha.lab234.dao.moneycard.constant.MoneyAccountFields;
import com.kerrrusha.lab234.dao.moneycard.constant.Queries;
import com.kerrrusha.lab234.model.MoneyAccount;
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

	public MoneyCard findOneByUserIdAndSecretAndNumber(int userId, String secret, String number) throws DBException {
		MoneyCard entity = null;
		try (Connection con = DriverManager.getConnection(FULL_URL);
			 PreparedStatement stmt = con.prepareStatement(Queries.FIND_MONEY_ACCOUNT_BY_USER_ID_AND_NAME)) {
			stmt.setInt(1, userId);
			stmt.setString(2, secret);
			stmt.setString(3, number);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					entity = mapToEntity(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
		return entity;
	}

	public MoneyAccount findMoneyAccountByUserIdAndName(int userId, String name) throws DBException {
		MoneyAccount moneyAccount = null;
		try (Connection con = DriverManager.getConnection(FULL_URL);
			 PreparedStatement stmt = con.prepareStatement(Queries.FIND_MONEY_ACCOUNT_BY_USER_ID_AND_NAME)) {
			stmt.setInt(1, userId);
			stmt.setString(2, name);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					moneyAccount = mapToMoneyAccount(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
		return moneyAccount;
	}

	public void insert(MoneyCard entity) throws DBException {
		try (Connection con = DriverManager.getConnection(FULL_URL);
			 PreparedStatement stmt = con.prepareStatement(Queries.INSERT_MONEY_CARD)) {
			stmt.setInt(1, entity.getMoneyAccountId());
			stmt.setString(2, entity.getNumber());
			stmt.setTimestamp(3, Timestamp.valueOf(entity.getExpirationDate().atStartOfDay()));
			stmt.setString(4, entity.getSecret());
			stmt.setInt(5, entity.getBalance());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
	}

	public void insertMoneyAccount(MoneyAccount newMoneyAccount) throws DBException {
		try (Connection con = DriverManager.getConnection(FULL_URL);
			 PreparedStatement stmt = con.prepareStatement(Queries.INSERT_MONEY_ACCOUNT)) {
			stmt.setString(1, newMoneyAccount.getName());
			stmt.setInt(2, newMoneyAccount.getOwnerUserId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
	}

	public void updateNumber(MoneyCard entity) throws DBException {
		try (Connection con = DriverManager.getConnection(FULL_URL);
			 PreparedStatement stmt = con.prepareStatement(Queries.UPDATE_MONEY_CARD_NUMBER)) {
			stmt.setString(1, entity.getNumber());
			stmt.setInt(2, entity.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
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

	private MoneyAccount mapToMoneyAccount(ResultSet rs) throws SQLException {
		MoneyAccount moneyAccount = new MoneyAccount();

		moneyAccount.setId(rs.getInt(MoneyAccountFields.MONEY_ACCOUNT_ID));
		moneyAccount.setName(rs.getString(MoneyAccountFields.MONEY_ACCOUNT_NAME));
		moneyAccount.setOwnerUserId(rs.getInt(MoneyAccountFields.MONEY_ACCOUNT_OWNER_USER_ID));
		moneyAccount.setCreatedTime(mapToLocalDateTime(rs.getTimestamp(MoneyAccountFields.MONEY_ACCOUNT_CREATED_TIME)));
		moneyAccount.setUpdatedTime(mapToLocalDateTime(rs.getTimestamp(MoneyAccountFields.MONEY_ACCOUNT_UPDATED_TIME)));

		return moneyAccount;
	}
}
