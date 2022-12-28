package com.kerrrusha.lab234.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BlockedMoneyAccountTest {

    @Test
    public void isEqualTest() {
        assertEquals(getExample1(), getExample1());
    }

    @Test
    public void isEqual1Test() {
        assertEquals(getExample2(), getExample2());
    }

    @Test
    public void isNotEqualTest() {
        assertNotEquals(getExample1(), getExample2());
    }

    private BlockedMoneyAccount getExample1() {
        BlockedMoneyAccount example = new BlockedMoneyAccount();

        example.setId(1);
        example.setBlockedMoneyAccountId(1);
        example.setBlockedByUserId(1);

        return example;
    }

    private BlockedMoneyAccount getExample2() {
        BlockedMoneyAccount example = new BlockedMoneyAccount();

        example.setId(2);
        example.setBlockedMoneyAccountId(2);
        example.setBlockedByUserId(2);

        return example;
    }
}
