package com.kerrrusha.lab234.util;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqlUtilTest {

    @Test
    public void mapToLocalDateTimeTest() {
        final LocalDateTime expected = getExampleLocalDateTime();
        final LocalDateTime actual = SqlUtil.mapToLocalDateTime(Timestamp.valueOf(expected));

        assertEquals(expected, actual);
    }

    @Test
    public void mapToLocalDateTest() {
        final LocalDate expected = getExampleLocalDate();
        final LocalDate actual = SqlUtil.mapToLocalDate(Timestamp.valueOf(getExampleLocalDateTime()));

        assertEquals(expected, actual);
    }

    private LocalDateTime getExampleLocalDateTime() {
        return LocalDateTime.of(2022, 12, 28, 20, 27, 30);
    }

    private LocalDate getExampleLocalDate() {
        return LocalDate.of(2022, 12, 28);
    }
}
