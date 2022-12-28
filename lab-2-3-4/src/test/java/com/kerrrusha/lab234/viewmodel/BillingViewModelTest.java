package com.kerrrusha.lab234.viewmodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillingViewModelTest {

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

    private BillingViewModel getExample1() {
        BillingViewModel example = new BillingViewModel();

        example.setBillingId(1);
        example.setStatusName("First");
        example.setMoneyAmount(100);
        example.setToCardNumber("1111 1111 1111 1111");
        example.setFromCardNumber("2222 2222 2222 2222");

        return example;
    }

    private BillingViewModel getExample2() {
        BillingViewModel example = new BillingViewModel();

        example.setBillingId(2);
        example.setStatusName("Second");
        example.setMoneyAmount(200);
        example.setToCardNumber("3333 3333 3333 3333");
        example.setFromCardNumber("4444 4444 4444 4444");

        return example;
    }
}