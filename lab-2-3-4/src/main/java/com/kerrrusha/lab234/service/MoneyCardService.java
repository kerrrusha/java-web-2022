package com.kerrrusha.lab234.service;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.moneycard.MoneyCardDao;
import com.kerrrusha.lab234.model.MoneyCard;
import com.kerrrusha.lab234.model.User;

import java.util.Collection;

public class MoneyCardService {

    private final MoneyCardDao moneyCardDao;
    private final User user;

    public MoneyCardService(User user) throws DBException {
        moneyCardDao = new MoneyCardDao();
        this.user = user;
    }

    public Collection<MoneyCard> getUserMoneyCards() throws DBException {
        return moneyCardDao.findByUserId(user.getId());
    }
}
