package com.kerrrusha.lab234.service.moneycard;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kerrrusha.lab234.util.ServletUtil.setJsonToResponse;

public class OpenMoneyCardResultSender extends OpenMoneyCardResult {

	private final Gson gson = new Gson();

	public static OpenMoneyCardResultSender valueOf(OpenMoneyCardResult anotherResult) {
		OpenMoneyCardResultSender sender = new OpenMoneyCardResultSender();

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