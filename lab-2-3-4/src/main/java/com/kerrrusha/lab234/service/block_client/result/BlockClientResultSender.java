package com.kerrrusha.lab234.service.block_client.result;

import com.google.gson.Gson;
import com.kerrrusha.lab234.factory.GsonFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.kerrrusha.lab234.util.ServletUtil.setJsonToResponse;

public class BlockClientResultSender extends BlockClientResult {

	private final Gson gson = GsonFactory.create();

	public static BlockClientResultSender valueOf(BlockClientResult anotherResult) {
		BlockClientResultSender sender = new BlockClientResultSender();

		sender.setStatus(anotherResult.getStatus());
		sender.setErrorPool(anotherResult.getErrorPool());
		sender.setClient(anotherResult.getClient());

		return sender;
	}

	public void sendResponse(HttpServletResponse response) throws IOException {
		setJsonToResponse(response, gson.toJson(this));
		response.getWriter().flush();
	}
}
