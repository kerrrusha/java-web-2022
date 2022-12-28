package com.kerrrusha.lab234.model.adapter;

import com.google.gson.Gson;
import com.kerrrusha.lab234.factory.GsonFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateTimeAdapterTest {

    @Test
    public void writeTest() {
        final Gson gson = GsonFactory.create();
        final LocalDateTime dateTime = LocalDateTime.of(2022, 12, 28, 19, 52, 10);

        final String expected = "\"2022-12-28T19:52:10\"";
        final String actual = gson.toJson(dateTime);

        assertEquals(expected, actual);
    }

    @Test
    public void readTest() {
        final Gson gson = GsonFactory.create();
        final String dateTime = "\"2022-12-28T19:52:10\"";

        final LocalDateTime expected = LocalDateTime.of(2022, 12, 28, 19, 52, 10);
        final LocalDateTime actual = gson.fromJson(dateTime, LocalDateTime.class);

        assertEquals(expected, actual);
    }
}
