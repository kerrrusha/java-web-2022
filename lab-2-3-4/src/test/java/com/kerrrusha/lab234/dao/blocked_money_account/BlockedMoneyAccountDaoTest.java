package com.kerrrusha.lab234.dao.blocked_money_account;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.model.BlockedMoneyAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockedMoneyAccountDaoTest {

    private final BlockedMoneyAccountDao dao;

    public BlockedMoneyAccountDaoTest() throws DBException {
        dao = new BlockedMoneyAccountDao();
    }

    @Test
    public void insertDeleteTest() throws DBException {
        final BlockedMoneyAccount example = getExample();

        dao.insert(example.getBlockedMoneyAccountId(), example.getBlockedByUserId());
        assertTrue(dao.entryByMoneyAccountIdExists(example.getBlockedMoneyAccountId()));
        dao.delete(example.getBlockedMoneyAccountId());
    }

    private BlockedMoneyAccount getExample() {
        BlockedMoneyAccount example = new BlockedMoneyAccount();

        example.setBlockedByUserId(1);
        example.setBlockedMoneyAccountId(1);

        return example;
    }
}