package com.kerrrusha.lab234.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyCardFactoryTest {

    @Test
    public void createdTest() {
        assertNotNull(MoneyCardFactory.createNewMoneyCard(1));
    }
}