package com.kerrrusha.lab234.service;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.blocked_money_account.BlockedMoneyAccountDao;
import com.kerrrusha.lab234.dao.moneycard.MoneyCardDao;
import com.kerrrusha.lab234.exception.BlockMoneyAccountException;
import com.kerrrusha.lab234.model.MoneyCard;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.validator.MoneyAccountValidator;

import java.util.Collection;

public class BlockMoneyAccountService {

    private final BlockedMoneyAccountDao blockedMoneyAccountDao;
    private final MoneyCardDao moneyCardDao;
    private final User user;

    public BlockMoneyAccountService(User user) throws DBException {
        blockedMoneyAccountDao = new BlockedMoneyAccountDao();
        moneyCardDao = new MoneyCardDao();
        this.user = user;
    }

    public MoneyCard blockMoneyAccount(String moneyAccountIdStr) throws DBException, BlockMoneyAccountException {
        int moneyAccountId = Integer.parseInt(moneyAccountIdStr);
        Collection<String> errorPool = new MoneyAccountValidator(user, moneyAccountId).getErrors();
        if (!errorPool.isEmpty()) {
            throw new BlockMoneyAccountException(errorPool);
        }
        blockedMoneyAccountDao.insert(moneyAccountId, user.getId());
        return moneyCardDao.findMoneyCardByMoneyAccountId(moneyAccountId);
    }
}
