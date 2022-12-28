package com.kerrrusha.lab234.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BillingStatusTest {

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

    private BillingStatus getExample1() {
        BillingStatus example = new BillingStatus();

        example.setId(1);
        example.setName("First");

        return example;
    }

    private BillingStatus getExample2() {
        BillingStatus example = new BillingStatus();

        example.setId(2);
        example.setName("Second");

        return example;
    }
}
