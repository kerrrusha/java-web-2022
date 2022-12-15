package com.kerrrusha.lab234.controller.auth;

import com.kerrrusha.lab234.service.auth.AuthService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/signout")
public class SignoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new AuthService(request).signout();
        response.sendRedirect(getServletContext().getContextPath());
    }
}
