package com.kerrrusha.lab234.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SignoutServlet", urlPatterns = "/signout")
public class SignoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //do signout ...
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
