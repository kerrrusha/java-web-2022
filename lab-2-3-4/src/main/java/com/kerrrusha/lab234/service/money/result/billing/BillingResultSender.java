package com.kerrrusha.lab234.service.money.result.billing;

import com.google.gson.Gson;
import com.kerrrusha.lab234.factory.GsonFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kerrrusha.lab234.util.ServletUtil.setJsonToResponse;

public class BillingResultSender extends BillingResult {

	private final Gson gson = GsonFactory.create();

	public static BillingResultSender valueOf(BillingResult anotherResult) {
		BillingResultSender sender = new BillingResultSender();

		sender.setStatus(anotherResult.getStatus());
		sender.setErrorPool(anotherResult.getErrorPool());
		sender.setMoneyCard(anotherResult.getMoneyCard());

		return sender;
	}

	public void sendResponse(HttpServletResponse response) throws IOException {
		setJsonToResponse(response, gson.toJson(this));
		response.getWriter().flush();
	}
}
