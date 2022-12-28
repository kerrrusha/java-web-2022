package com.kerrrusha.lab234.util;

import org.junit.jupiter.api.Test;

import static com.kerrrusha.lab234.util.RandomUtil.generateRandomNDigits;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomUtilTest {

    @Test
    public void generateRandomNDigitsTest() {
        final int TESTS_AMOUNT = 100;
        final int DIGITS_AMOUNT = 3;

        for (int i = 0; i < TESTS_AMOUNT; i++) {
            assertEquals(DIGITS_AMOUNT, generateRandomNDigits(DIGITS_AMOUNT).length());
        }
    }
}
