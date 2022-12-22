package com.kerrrusha.lab234.factory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kerrrusha.lab234.model.adapter.LocalDateAdapter;
import com.kerrrusha.lab234.model.adapter.LocalDateTimeAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GsonFactory {

    public static Gson create() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter().nullSafe())
                .setPrettyPrinting()
                .create();
    }
}
