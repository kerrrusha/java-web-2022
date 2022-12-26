package com.kerrrusha.lab234.dao.blocked_user;

import com.kerrrusha.lab234.dao.AbstractDao;
import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.blocked_user.constant.Fields;
import com.kerrrusha.lab234.dao.blocked_user.constant.Queries;
import com.kerrrusha.lab234.model.BlockedUser;

import java.sql.*;

import static com.kerrrusha.lab234.util.SqlUtil.mapToLocalDateTime;

public class BlockedUserDao extends AbstractDao {

	public BlockedUserDao() throws DBException {}

	public void insert(int userId, int blockedByUserId) throws DBException {
		try (Connection con = DriverManager.getConnection(FULL_URL);
			 PreparedStatement stmt = con.prepareStatement(Queries.INSERT_BLOCKED_USER)) {
			stmt.setInt(1, userId);
			stmt.setInt(2, blockedByUserId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
	}

	public void delete(int userId) throws DBException {
		try (Connection con = DriverManager.getConnection(FULL_URL);
			 PreparedStatement stmt = con.prepareStatement(Queries.DELETE_BLOCKED_USER)) {
			stmt.setInt(1, userId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
	}

	public boolean entryByUserIdExists(int moneyAccountId) throws DBException {
		BlockedUser entity = null;
		try (Connection con = DriverManager.getConnection(FULL_URL);
			 PreparedStatement stmt = con.prepareStatement(Queries.FIND_BY_BLOCKED_USER_ID)) {
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

	private BlockedUser mapToEntity(ResultSet rs) throws SQLException {
		BlockedUser entity = new BlockedUser();

		entity.setId(rs.getInt(Fields.BLOCKED_USER_ID));
		entity.setBlockedUserId(rs.getInt(Fields.BLOCKED_USER_USER_ID));
		entity.setBlockedByUserId(rs.getInt(Fields.BLOCKED_USER_BLOCKED_BY_USER_ID));
		entity.setCreatedTime(mapToLocalDateTime(rs.getTimestamp(Fields.BLOCKED_USER_CREATED_TIME)));

		return entity;
	}
}
