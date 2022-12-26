package com.kerrrusha.lab234.exception;

import java.util.Collection;

public class BlockClientException extends ErrorPoolException {

    public BlockClientException(Collection<String> errorPool) {
        super(errorPool);
    }
}
