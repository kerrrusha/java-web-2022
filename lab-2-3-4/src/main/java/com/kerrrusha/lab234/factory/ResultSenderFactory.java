package com.kerrrusha.lab234.factory;

import com.kerrrusha.lab234.service.AbstractResult;
import com.kerrrusha.lab234.service.AbstractResultSender;

import static java.util.Collections.singletonList;

public class ResultSenderFactory {

    public static AbstractResultSender createAbstractResultSenderFromError(String error, int status) {
        AbstractResult result = new AbstractResult();
        result.setErrorPool(singletonList(error));
        result.setStatus(status);
        return AbstractResultSender.valueOf(result);
    }
}
