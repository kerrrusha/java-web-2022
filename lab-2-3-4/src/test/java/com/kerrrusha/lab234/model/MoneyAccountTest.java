package com.kerrrusha.lab234.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyAccountTest {

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

    private MoneyAccount getExample1() {
        MoneyAccount example = new MoneyAccount();

        example.setId(1);
        example.setName("First");
        example.setOwnerUserId(1);

        return example;
    }

    private MoneyAccount getExample2() {
        MoneyAccount example = new MoneyAccount();

        example.setId(2);
        example.setName("Second");
        example.setOwnerUserId(2);

        return example;
    }
}
