package com.kerrrusha.lab234.service.auth;

import com.google.gson.Gson;
import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.service.auth.result.AuthResult;
import org.apache.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.kerrrusha.lab234.util.ServletUtil.setJsonToResponse;

public class AuthResultSender extends AuthResult {

	private final Gson gson = new Gson();

	public static AuthResultSender valueOf(AuthResult anotherResult) {
		AuthResultSender sender = new AuthResultSender();

		sender.setStatus(anotherResult.getStatus());
		sender.setErrorPool(anotherResult.getErrorPool());
		sender.setUser(anotherResult.getUser());

		return sender;
	}

	public void sendResponse(HttpServletResponse response) throws IOException {
		setJsonToResponse(response, gson.toJson(this));
		response.getWriter().flush();
	}
}