package com.kerrrusha.lab234.controller.auth;

import com.kerrrusha.lab234.service.auth.AuthResultSender;
import com.kerrrusha.lab234.service.auth.LoginService;
import com.kerrrusha.lab234.service.auth.AuthResult;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String phone = request.getParameter("phone");
		final String password = request.getParameter("password");

		AuthResult result = new LoginService(phone, password).processLogin(request);
		AuthResultSender.valueOf(result).sendResponse(response);
	}
}
