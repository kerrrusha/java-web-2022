package com.kerrrusha.lab234.exception;

import java.util.Collection;

public class RestrictedMoneyAccountException extends ErrorPoolException {

    public RestrictedMoneyAccountException(Collection<String> errorPool) {
        super(errorPool);
    }
}
