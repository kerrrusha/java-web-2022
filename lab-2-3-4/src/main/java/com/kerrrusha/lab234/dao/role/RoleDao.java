package com.kerrrusha.lab234.dao.role;

import com.kerrrusha.lab234.dao.AbstractDao;
import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.role.constant.Fields;
import com.kerrrusha.lab234.dao.role.constant.Queries;
import com.kerrrusha.lab234.model.Role;

import java.sql.*;

public class RoleDao extends AbstractDao {

	public RoleDao() throws DBException {}

	public Role findOneById(int id) throws DBException {
		Role entity = null;
		try (Connection con = DriverManager.getConnection(FULL_URL);
		     PreparedStatement stmt = con.prepareStatement(Queries.FIND_ROLE_BY_ID)) {
			stmt.setInt(1, id);
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

	private Role mapToEntity(ResultSet rs) throws SQLException {
		Role entity = new Role();

		entity.setId(rs.getInt(Fields.ROLE_ID));
		entity.setName(rs.getString(Fields.ROLE_NAME));

		return entity;
	}
}
