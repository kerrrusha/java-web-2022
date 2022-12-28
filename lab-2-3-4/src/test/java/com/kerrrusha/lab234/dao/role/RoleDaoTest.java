package com.kerrrusha.lab234.dao.role;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.model.Role;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

class RoleDaoTest {

    private static final Logger logger = Logger.getLogger(RoleDaoTest.class);

    private final RoleDao dao;

    public RoleDaoTest() throws DBException {
        dao = new RoleDao();
    }

    @Test
    public void findByUserIdTest() throws DBException {
        Role entity = dao.findOneById(1);
        logger.info("Collected next role by id = 1:" + System.lineSeparator() + entity);
    }
}