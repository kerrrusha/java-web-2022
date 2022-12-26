package com.kerrrusha.lab234.dao.blocked_money_account;

import com.kerrrusha.lab234.dao.AbstractDao;
import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.blocked_money_account.constant.Fields;
import com.kerrrusha.lab234.dao.blocked_money_account.constant.Queries;
import com.kerrrusha.lab234.model.BlockedMoneyAccount;

import java.sql.*;

import static com.kerrrusha.lab234.util.SqlUtil.mapToLocalDateTime;

public class BlockedMoneyAccountDao extends AbstractDao {

	public BlockedMoneyAccountDao() throws DBException {}

	public void insert(int moneyAccountId, int blockedByUserId) throws DBException {
		try (Connection con = DriverManager.getConnection(FULL_URL);
			 PreparedStatement stmt = con.prepareStatement(Queries.INSERT_BLOCKED_MONEY_ACCOUNT)) {
			stmt.setInt(1, moneyAccountId);
			stmt.setInt(2, blockedByUserId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
	}

	public boolean entryByMoneyAccountIdExists(int moneyAccountId) throws DBException {
		BlockedMoneyAccount entity = null;
		try (Connection con = DriverManager.getConnection(FULL_URL);
			 PreparedStatement stmt = con.prepareStatement(Queries.FIND_BY_BLOCKED_MONEY_ACCOUNT_ID)) {
			stmt.setInt(1, moneyAccountId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					entity = mapToEntity(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
		return entity != null;
	}

	private BlockedMoneyAccount mapToEntity(ResultSet rs) throws SQLException {
		BlockedMoneyAccount entity = new BlockedMoneyAccount();

		entity.setId(rs.getInt(Fields.BLOCKED_MONEY_ACCOUNT_ID));
		entity.setBlockedMoneyAccountId(rs.getInt(Fields.BLOCKED_MONEY_ACCOUNT_BLOCKED_MONEY_ACCOUNT_ID));
		entity.setBlockedByUserId(rs.getInt(Fields.BLOCKED_MONEY_ACCOUNT_BLOCKED_BY_USER_ID));
		entity.setCreatedTime(mapToLocalDateTime(rs.getTimestamp(Fields.BLOCKED_MONEY_ACCOUNT_CREATED_TIME)));

		return entity;
	}
}
