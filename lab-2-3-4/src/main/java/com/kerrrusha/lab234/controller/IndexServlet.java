package com.kerrrusha.lab234.controller;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class IndexServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}