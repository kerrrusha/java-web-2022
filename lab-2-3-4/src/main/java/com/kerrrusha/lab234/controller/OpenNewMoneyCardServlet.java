package com.kerrrusha.lab234.controller;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.factory.ResultSenderFactory;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.service.moneycard.MoneyService;
import com.kerrrusha.lab234.service.moneycard.result.open_new_card.OpenMoneyCardResult;
import com.kerrrusha.lab234.service.moneycard.result.open_new_card.OpenMoneyCardResultSender;
import org.apache.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/open-new-money-card")
public class OpenNewMoneyCardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MoneyService moneyService;
        try {
            moneyService = new MoneyService((User)request.getSession().getAttribute("user"));
        } catch (DBException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        request.setAttribute("moneyService", moneyService);
        request.getRequestDispatcher("open-new-money-card.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String moneyAccountName = request.getParameter("moneyAccountName");
        try {
            MoneyService moneyService = new MoneyService((User)request.getSession().getAttribute("user"));
            OpenMoneyCardResult result = moneyService.openNewMoneyCard(moneyAccountName);
            OpenMoneyCardResultSender.valueOf(result).sendResponse(response);
        } catch (DBException e) {
            ResultSenderFactory.createAbstractResultSenderFromError(e.getMessage(), HttpStatus.SC_INTERNAL_SERVER_ERROR).sendResponse(response);
        }
    }
}
