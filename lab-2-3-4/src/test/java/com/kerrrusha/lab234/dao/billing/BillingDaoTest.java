package com.kerrrusha.lab234.dao.billing;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.model.Billing;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class BillingDaoTest {

    private static final Logger logger = Logger.getLogger(BillingDaoTest.class);

    private final BillingDao dao;

    public BillingDaoTest() throws DBException {
        dao = new BillingDao();
    }

    @Test
    public void entitiesByIdCountTest() throws DBException {
        Collection<Billing> entities = dao.findBillingsByUserId(1);
        logger.info("Collected " + entities.size() + " entities from user.id = 1.");
    }
}
