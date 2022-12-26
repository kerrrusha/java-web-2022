package com.kerrrusha.lab234.service.block_money_account;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.dao.blocked_money_account.BlockedMoneyAccountDao;
import com.kerrrusha.lab234.dao.moneycard.MoneyCardDao;
import com.kerrrusha.lab234.exception.BlockMoneyAccountException;
import com.kerrrusha.lab234.model.MoneyCard;
import com.kerrrusha.lab234.model.MoneyAccount;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.service.block_money_account.result.BlockMoneyAccountResult;
import com.kerrrusha.lab234.validator.MoneyAccountValidator;
import org.apache.http.HttpStatus;

import java.util.Collection;

import static java.util.Collections.singletonList;

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

    public BlockMoneyAccountResult blockMoneyAccountAsAdmin(String moneyAccountIdStr) {
        BlockMoneyAccountResult result = new BlockMoneyAccountResult();
        MoneyAccount blockedMoneyAccount;
        try {
            int moneyAccountId = Integer.parseInt(moneyAccountIdStr);
            MoneyAccountValidator validator = new MoneyAccountValidator(user, moneyAccountId);
            Collection<String> errorPool = validator.getErrors();
            if (!errorPool.isEmpty() && !validator.onlyErrorRestrictedMoneyAccount()) {
                throw new BlockMoneyAccountException(errorPool);
            }
            blockedMoneyAccountDao.insert(moneyAccountId, user.getId());
            MoneyCard blockedMoneyCard = moneyCardDao.findMoneyCardByMoneyAccountId(moneyAccountId);
            blockedMoneyAccount = moneyCardDao.findMoneyAccountById(blockedMoneyCard.getMoneyAccountId());
        } catch (BlockMoneyAccountException | NumberFormatException | DBException e) {
            result.setStatus(HttpStatus.SC_CONFLICT);
            result.setErrorPool(singletonList(e.getMessage()));
            return result;
        }
        result.setStatus(HttpStatus.SC_OK);
        result.setMoneyAccount(blockedMoneyAccount);
        return result;
    }

    public BlockMoneyAccountResult unblockMoneyAccountAsAdmin(String moneyAccountIdStr) {
        BlockMoneyAccountResult result = new BlockMoneyAccountResult();
        MoneyAccount unblockedMoneyAccount;
        try {
            int moneyAccountId = Integer.parseInt(moneyAccountIdStr);
            MoneyAccountValidator validator = new MoneyAccountValidator(user, moneyAccountId);
            Collection<String> errorPool = validator.getErrors();
            if (!errorPool.isEmpty() && validator.onlyErrorRestrictedMoneyAccount()) {
                throw new BlockMoneyAccountException(errorPool);
            }
            blockedMoneyAccountDao.delete(moneyAccountId);
            MoneyCard unblockedMoneyCard = moneyCardDao.findMoneyCardByMoneyAccountId(moneyAccountId);
            unblockedMoneyAccount = moneyCardDao.findMoneyAccountById(unblockedMoneyCard.getMoneyAccountId());
        } catch (BlockMoneyAccountException | NumberFormatException | DBException e) {
            result.setStatus(HttpStatus.SC_CONFLICT);
            result.setErrorPool(singletonList(e.getMessage()));
            return result;
        }
        result.setStatus(HttpStatus.SC_OK);
        result.setMoneyAccount(unblockedMoneyAccount);
        return result;
    }
}
