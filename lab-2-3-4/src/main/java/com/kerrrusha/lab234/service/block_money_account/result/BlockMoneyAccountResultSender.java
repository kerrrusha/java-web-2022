package com.kerrrusha.lab234.service.block_money_account.result;

import com.google.gson.Gson;
import com.kerrrusha.lab234.factory.GsonFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kerrrusha.lab234.util.ServletUtil.setJsonToResponse;

public class BlockMoneyAccountResultSender extends BlockMoneyAccountResult {

	private final Gson gson = GsonFactory.create();

	public static BlockMoneyAccountResultSender valueOf(BlockMoneyAccountResult anotherResult) {
		BlockMoneyAccountResultSender sender = new BlockMoneyAccountResultSender();

		sender.setStatus(anotherResult.getStatus());
		sender.setErrorPool(anotherResult.getErrorPool());
		sender.setMoneyAccount(anotherResult.getMoneyAccount());

		return sender;
	}

	public void sendResponse(HttpServletResponse response) throws IOException {
		setJsonToResponse(response, gson.toJson(this));
		response.getWriter().flush();
	}
}
