package com.kerrrusha.lab234.util;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorUtilTest {

    private static final String DEFAULT_ERROR_MESSAGE = EMPTY;

    @Test
    public void checkIfFieldIsNullTest() {
        final Optional<String> expected = Optional.of(DEFAULT_ERROR_MESSAGE);
        final Optional<String> actual = ValidatorUtil.checkIfFieldIsNull(null, DEFAULT_ERROR_MESSAGE);

        assertEquals(expected, actual);
    }

    @Test
    public void checkIfFieldIsNotNullTest() {
        final Optional<String> expected = Optional.empty();
        final Optional<String> actual = ValidatorUtil.checkIfFieldIsNull(new Object(), DEFAULT_ERROR_MESSAGE);

        assertEquals(expected, actual);
    }

    @Test
    public void checkIfStringIsBlankTest() {
        final Optional<String> expected = Optional.of(DEFAULT_ERROR_MESSAGE);
        final Optional<String> actual = ValidatorUtil.checkIfStringIsBlank(null, DEFAULT_ERROR_MESSAGE);

        assertEquals(expected, actual);
    }

    @Test
    public void checkIfStringIsBlank1Test() {
        final Optional<String> expected = Optional.of(DEFAULT_ERROR_MESSAGE);
        final Optional<String> actual = ValidatorUtil.checkIfStringIsBlank(EMPTY, DEFAULT_ERROR_MESSAGE);

        assertEquals(expected, actual);
    }

    @Test
    public void checkIfStringIsNotBlankTest() {
        final Optional<String> expected = Optional.empty();
        final Optional<String> actual = ValidatorUtil.checkIfFieldIsNull("test", DEFAULT_ERROR_MESSAGE);

        assertEquals(expected, actual);
    }
}
