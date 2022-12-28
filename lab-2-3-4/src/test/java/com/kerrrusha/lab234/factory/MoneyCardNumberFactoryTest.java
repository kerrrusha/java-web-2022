package com.kerrrusha.lab234.factory;

import org.junit.jupiter.api.Test;

import static com.kerrrusha.lab234.factory.MoneyCardNumberFactory.createNumber;
import static com.kerrrusha.lab234.factory.MoneyCardNumberFactory.getDigitsAmount;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyCardNumberFactoryTest {

    @Test
    public void getDigitsAmountTest() {
        assertEquals(getDigitsAmount(123), 3);
        assertEquals(getDigitsAmount(0), 1);
        assertEquals(getDigitsAmount(-1), 2);
        assertEquals(getDigitsAmount(12), 2);
        assertEquals(getDigitsAmount(12345), 5);
    }

    @Test
    public void createNumberTest() {
        assertEquals(createNumber(1234), "0000 0000 0000 1234");
        assertEquals(createNumber(0), "0000 0000 0000 0000");
        assertEquals(createNumber(1234567891011123L), "1234 5678 9101 1123");
    }
}
