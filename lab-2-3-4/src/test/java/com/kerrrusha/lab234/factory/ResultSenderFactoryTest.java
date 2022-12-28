package com.kerrrusha.lab234.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultSenderFactoryTest {

    @Test
    public void createdTest() {
        assertNotNull(ResultSenderFactory.createAbstractResultSenderFromError("Not found", 404));
    }
}