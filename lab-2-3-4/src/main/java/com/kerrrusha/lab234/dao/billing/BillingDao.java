package com.kerrrusha.lab234.dao.billing;

import com.kerrrusha.lab234.dao.AbstractDao;
import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.billing.constant.BillingStatusFields;
import com.kerrrusha.lab234.dao.billing.constant.Fields;
import com.kerrrusha.lab234.dao.billing.constant.Queries;
import com.kerrrusha.lab234.model.Billing;
import com.kerrrusha.lab234.model.BillingStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

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

	public Collection<Billing> findBillingsByUserId(int userId) throws DBException {
		Collection<Billing> entities = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(FULL_URL);
			 PreparedStatement stmt = con.prepareStatement(Queries.FIND_BILLINS_BY_USER_ID)) {
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

	public BillingStatus findBillingStatusById(int billingStatusId) throws DBException {
		BillingStatus entity = null;
		try (Connection con = DriverManager.getConnection(FULL_URL);
			 PreparedStatement stmt = con.prepareStatement(Queries.FIND_BILLING_STATUS_BY_ID)) {
			stmt.setInt(1, billingStatusId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					entity = mapToBillingStatus(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e.getMessage());
		}
		return entity;
	}

	private BillingStatus mapToBillingStatus(ResultSet rs) throws SQLException {
		BillingStatus billingStatus = new BillingStatus();

		billingStatus.setId(rs.getInt(BillingStatusFields.BILLING_STATUS_ID));
		billingStatus.setName(rs.getString(BillingStatusFields.BILLING_STATUS_NAME));

		return billingStatus;
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
