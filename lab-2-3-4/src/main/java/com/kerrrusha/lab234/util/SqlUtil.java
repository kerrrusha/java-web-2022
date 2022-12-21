package com.kerrrusha.lab234.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SqlUtil {

    public static LocalDateTime mapToLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    public static LocalDate mapToLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
