package com.kerrrusha.lab234.exception;

import java.util.Collection;

public class BlockMoneyAccountException extends ErrorPoolException {

    public BlockMoneyAccountException(Collection<String> errorPool) {
        super(errorPool);
    }
}
