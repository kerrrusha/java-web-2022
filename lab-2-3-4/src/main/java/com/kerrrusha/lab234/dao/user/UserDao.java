package com.kerrrusha.lab234.dao.user;

import com.kerrrusha.lab234.dao.AbstractDao;
import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.dao.user.constant.Fields;
import com.kerrrusha.lab234.dao.user.constant.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserDao extends AbstractDao {

	public UserDao() throws DBException {}

	public Collection<User> findAll() throws DBException {
		Collection<User> entities = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(FULL_URL);
		     Statement stmt = con.createStatement();
		     ResultSet rs = stmt.executeQuery(Queries.SELECT_ALL_USERS)) {
			while(rs.next()) {
				entities.add(mapToUser(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
		return entities;
	}

	public User findOneById(int id) throws DBException {
		User entity = null;
		try (Connection con = DriverManager.getConnection(FULL_URL);
		     PreparedStatement stmt = con.prepareStatement(Queries.FIND_USER_BY_ID)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					entity = mapToUser(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
		return entity;
	}

	public User findOneByPhone(String phone) throws DBException {
		User entity = null;
		try (Connection con = DriverManager.getConnection(FULL_URL);
		     PreparedStatement stmt = con.prepareStatement(Queries.FIND_USER_BY_PHONE)) {
			stmt.setString(1, phone);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					entity = mapToUser(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
		return entity;
	}

	public void insert(User entity) throws DBException {
		try (Connection con = DriverManager.getConnection(FULL_URL);
		     PreparedStatement stmt = con.prepareStatement(Queries.INSERT_USER)) {
			stmt.setString(1, entity.getFirstName());
			stmt.setString(2, entity.getLastName());
			stmt.setString(3, entity.getPhone());
			stmt.setString(4, entity.getPassword());
			stmt.setInt(5, entity.getRoleId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
	}

	public void deleteById(User entity) throws DBException {
		try (Connection con = DriverManager.getConnection(FULL_URL);
		     PreparedStatement stmt = con.prepareStatement(Queries.DELETE_USER_BY_ID)) {
			stmt.setInt(1, entity.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
	}

	public void deleteByPhone(User entity) throws DBException {
		try (Connection con = DriverManager.getConnection(FULL_URL);
		     PreparedStatement stmt = con.prepareStatement(Queries.DELETE_USER_BY_PHONE)) {
			stmt.setString(1, entity.getPhone());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
	}

	private User mapToUser(ResultSet rs) throws SQLException {
		User user = new User();

		user.setId(rs.getInt(Fields.USER_ID));
		user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
		user.setLastName(rs.getString(Fields.USER_LAST_NAME));
		user.setPhone(rs.getString(Fields.USER_PHONE));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
		user.setCreatedTime(rs.getTimestamp(Fields.USER_CREATED_TIME).toLocalDateTime());
		user.setUpdatedTime(rs.getTimestamp(Fields.USER_UPDATED_TIME).toLocalDateTime());

		return user;
	}
}
