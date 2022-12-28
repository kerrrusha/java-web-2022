package com.kerrrusha.lab234.model.adapter;

import com.google.gson.Gson;
import com.kerrrusha.lab234.factory.GsonFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateAdapterTest {

    @Test
    public void writeTest() {
        final Gson gson = GsonFactory.create();
        final LocalDate date = LocalDate.of(2022, 12, 28);

        final String expected = "\"2022-12-28\"";
        final String actual = gson.toJson(date);

        assertEquals(expected, actual);
    }

    @Test
    public void readTest() {
        final Gson gson = GsonFactory.create();
        final String jsonDate = "\"2022-12-28\"";

        final LocalDate expected = LocalDate.of(2022, 12, 28);
        final LocalDate actual = gson.fromJson(jsonDate, LocalDate.class);

        assertEquals(expected, actual);
    }
}
