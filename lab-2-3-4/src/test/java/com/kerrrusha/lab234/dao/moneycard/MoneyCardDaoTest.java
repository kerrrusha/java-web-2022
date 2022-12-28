package com.kerrrusha.lab234.dao.moneycard;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.model.MoneyCard;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class MoneyCardDaoTest {

    private static final Logger logger = Logger.getLogger(MoneyCardDaoTest.class);

    private final MoneyCardDao dao;

    public MoneyCardDaoTest() throws DBException {
        dao = new MoneyCardDao();
    }

    @Test
    public void findByUserIdTest() throws DBException {
        Collection<MoneyCard> entities = dao.findMoneyCardsByUserId(1);
        logger.info("Collected " + entities.size() + " entities.");
    }
}