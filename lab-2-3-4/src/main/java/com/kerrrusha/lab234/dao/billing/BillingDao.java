package com.kerrrusha.lab234.dao.billing;

import com.kerrrusha.lab234.dao.AbstractDao;
import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.billing.constant.Fields;
import com.kerrrusha.lab234.dao.billing.constant.Queries;
import com.kerrrusha.lab234.model.Billing;

import java.sql.*;

import static com.kerrrusha.lab234.util.SqlUtil.mapToLocalDateTime;

public class BillingDao extends AbstractDao {

	public BillingDao() throws DBException {}

	public void insertBilling(Billing entity) throws DBException {
		try (Connection con = DriverManager.getConnection(FULL_URL);
			 PreparedStatement stmt = con.prepareStatement(Queries.INSERT_BILLING)) {
			stmt.setInt(1, entity.getMoneyAmount());
			stmt.setInt(2, entity.getFromMoneyCardId());
			stmt.setInt(3, entity.getToMoneyCardId());
			stmt.setInt(4, entity.getBillingStatusId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
	}

	private Billing mapToEntity(ResultSet rs) throws SQLException {
		Billing entity = new Billing();

		entity.setId(rs.getInt(Fields.BILLING_ID));
		entity.setMoneyAmount(rs.getInt(Fields.BILLING_MONEY_AMOUNT));
		entity.setFromMoneyCardId(rs.getInt(Fields.BILLING_FROM_MONEY_CARD_ID));
		entity.setToMoneyCardId(rs.getInt(Fields.BILLING_TO_MONEY_CARD_ID));
		entity.setBillingStatusId(rs.getInt(Fields.BILLING_STATUS_ID));
		entity.setCreatedTime(mapToLocalDateTime(rs.getTimestamp(Fields.BILLING_CREATED_TIME)));

		return entity;
	}
}
