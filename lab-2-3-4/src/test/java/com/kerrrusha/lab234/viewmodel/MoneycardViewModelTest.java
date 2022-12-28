package com.kerrrusha.lab234.viewmodel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneycardViewModelTest {

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

    private MoneycardViewModel getExample1() {
        MoneycardViewModel example = new MoneycardViewModel();

        example.setMoneyAccountId(1);
        example.setBlocked(false);
        example.setSecret("634");
        example.setBalance(100);
        example.setMoneyCardNumber("1111 1111 1111 1111");

        return example;
    }

    private MoneycardViewModel getExample2() {
        MoneycardViewModel example = new MoneycardViewModel();

        example.setMoneyAccountId(2);
        example.setBlocked(true);
        example.setSecret("235");
        example.setBalance(200);
        example.setMoneyCardNumber("2222 2222 2222 2222");

        return example;
    }
}