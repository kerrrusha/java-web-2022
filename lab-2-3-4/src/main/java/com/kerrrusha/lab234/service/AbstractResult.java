package com.kerrrusha.lab234.service;

import java.util.Collection;

public class AbstractResult {

    protected Collection<String> errorPool;
    protected int status;

    public Collection<String> getErrorPool() {
        return errorPool;
    }

    public void setErrorPool(Collection<String> errorPool) {
        this.errorPool = errorPool;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
