package com.kerrrusha.lab234.exception;

import java.util.Collection;

import static java.util.stream.Collectors.joining;

public class ErrorPoolException extends Exception {

    public ErrorPoolException(Collection<String> errorPool) {
        super(errorPool.stream().collect(joining(System.lineSeparator())));
    }
}
