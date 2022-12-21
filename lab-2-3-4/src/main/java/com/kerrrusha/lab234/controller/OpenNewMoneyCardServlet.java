package com.kerrrusha.lab234.controller;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.service.MoneyCardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenNewMoneyCardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MoneyCardService moneyCardService;
        try {
            moneyCardService = new MoneyCardService((User)request.getSession().getAttribute("user"));
        } catch (DBException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        request.setAttribute("moneyCardService", moneyCardService);
        request.getRequestDispatcher("open-new-money-card.jsp").forward(request, response);
    }
}
