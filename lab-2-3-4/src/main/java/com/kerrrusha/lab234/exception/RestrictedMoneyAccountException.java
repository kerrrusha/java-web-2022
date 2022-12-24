package com.kerrrusha.lab234.exception;

import java.util.Collection;

import static java.util.stream.Collectors.joining;

public class RestrictedMoneyAccountException extends Exception {

    public RestrictedMoneyAccountException(Collection<String> errorPool) {
        super(errorPool.stream().collect(joining(System.lineSeparator())));
    }
}
