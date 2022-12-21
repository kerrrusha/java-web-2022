package com.kerrrusha.lab234.factory;

public class MoneyCardNumberFactory {

    private static final int NUMBER_DIGITS_AMOUNT = 16;
    private static final int DIGITS_GROUP_AMOUNT = 4;

    public static String createNumber(long value) {
        final int valueDigitsAmount = getDigitsAmount(value);
        if (valueDigitsAmount > NUMBER_DIGITS_AMOUNT) {
            return null;
        }
        String result = "0".repeat(Math.max(0, NUMBER_DIGITS_AMOUNT - valueDigitsAmount)) + value;
        return insertSpaces(result);
    }

    private static String insertSpaces(String result) {
        final int spacesAmount = NUMBER_DIGITS_AMOUNT / DIGITS_GROUP_AMOUNT - 1;
        for (int i = 0; i < spacesAmount; i++) {
            int startIndex = i * DIGITS_GROUP_AMOUNT + i;
            result = result.substring(0, startIndex + DIGITS_GROUP_AMOUNT) + " " + result.substring(startIndex + DIGITS_GROUP_AMOUNT);
        }
        return result;
    }

    public static int getDigitsAmount(long value) {
        return String.valueOf(value).length();
    }
}
