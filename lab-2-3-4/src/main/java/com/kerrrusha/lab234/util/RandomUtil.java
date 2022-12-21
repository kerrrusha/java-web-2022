package com.kerrrusha.lab234.util;

import org.apache.commons.lang3.RandomUtils;

public class RandomUtil {

    public static String generateRandomNDigits(int digitsAmount) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < digitsAmount; i++) {
            result.append(RandomUtils.nextInt(0, 10));
        }
        return result.toString();
    }
}
