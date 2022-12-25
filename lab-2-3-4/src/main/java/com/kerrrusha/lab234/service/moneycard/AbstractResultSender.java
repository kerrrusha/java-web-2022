package com.kerrrusha.lab234.service.moneycard;

import com.google.gson.Gson;
import com.kerrrusha.lab234.factory.GsonFactory;
import com.kerrrusha.lab234.service.AbstractResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kerrrusha.lab234.util.ServletUtil.setJsonToResponse;

public class AbstractResultSender extends AbstractResult {

    private final Gson gson = GsonFactory.create();

    public static AbstractResultSender valueOf(AbstractResult anotherResult) {
        AbstractResultSender sender = new AbstractResultSender();

        sender.setStatus(anotherResult.getStatus());
        sender.setErrorPool(anotherResult.getErrorPool());

        return sender;
    }

    public void sendResponse(HttpServletResponse response) throws IOException {
        setJsonToResponse(response, gson.toJson(this));
        response.getWriter().flush();
    }
}
