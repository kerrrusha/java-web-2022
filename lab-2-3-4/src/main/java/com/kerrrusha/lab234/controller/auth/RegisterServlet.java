package com.kerrrusha.lab234.controller.auth;

import com.kerrrusha.lab234.service.auth.AuthResultSender;
import com.kerrrusha.lab234.service.auth.RegisterService;
import com.kerrrusha.lab234.service.auth.result.AuthResult;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/auth/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String firstName = request.getParameter("firstName");
        final String lastName = request.getParameter("lastName");
        final String phone = request.getParameter("phone");
        final String password = request.getParameter("password");
        final String passwordRepeat = request.getParameter("passwordRepeat");
        final int roleId = 1;

        RegisterService service = new RegisterService(firstName, lastName, phone, password, passwordRepeat, roleId);
        AuthResult result = service.processRegister(request);
        AuthResultSender.valueOf(result).sendResponse(response);
    }
}
