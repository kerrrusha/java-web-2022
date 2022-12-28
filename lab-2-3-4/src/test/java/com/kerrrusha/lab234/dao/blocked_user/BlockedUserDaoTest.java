package com.kerrrusha.lab234.dao.blocked_user;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.model.BlockedUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockedUserDaoTest {

    private final BlockedUserDao dao;

    public BlockedUserDaoTest() throws DBException {
        dao = new BlockedUserDao();
    }

    @Test
    public void insertDeleteTest() throws DBException {
        final BlockedUser example = getExample();

        dao.insert(example.getBlockedUserId(), example.getBlockedByUserId());
        assertTrue(dao.entryByUserIdExists(example.getBlockedUserId()));
        dao.delete(example.getBlockedUserId());
    }

    private BlockedUser getExample() {
        BlockedUser example = new BlockedUser();

        example.setBlockedByUserId(1);
        example.setBlockedUserId(1);

        return example;
    }
}