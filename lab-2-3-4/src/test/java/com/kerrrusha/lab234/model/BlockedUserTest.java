package com.kerrrusha.lab234.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BlockedUserTest {

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

    private BlockedUser getExample1() {
        BlockedUser example = new BlockedUser();

        example.setId(1);
        example.setBlockedUserId(1);
        example.setBlockedByUserId(1);

        return example;
    }

    private BlockedUser getExample2() {
        BlockedUser example = new BlockedUser();

        example.setId(2);
        example.setBlockedUserId(2);
        example.setBlockedByUserId(2);

        return example;
    }
}
