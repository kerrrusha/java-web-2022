package com.kerrrusha.lab234.controller;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.exception.RestrictedMoneyAccountException;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.service.moneycard.MoneyCardService;
import com.kerrrusha.lab234.validator.BillingValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/create-billing")
public class CreateBillingServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String fromMoneyAccountIdStr = request.getParameter("fromMoneyAccountId");
        final User user = (User)request.getSession().getAttribute("user");

        try {
            int fromMoneyAccountId = Integer.parseInt(fromMoneyAccountIdStr);
            Collection<String> errorPool = new BillingValidator(user, fromMoneyAccountId).getErrors();
            if (!errorPool.isEmpty()) {
                throw new RestrictedMoneyAccountException(errorPool);
            }
            MoneyCardService moneyCardService = new MoneyCardService(user);

            request.setAttribute("moneyAccount", moneyCardService.getMoneyAccountById(fromMoneyAccountId));
            request.setAttribute("moneyCard", moneyCardService.getMoneyCardByMoneyAccountId(fromMoneyAccountId));
        } catch (NumberFormatException | DBException | RestrictedMoneyAccountException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("create-billing.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String fromMoneyAccountId = request.getParameter("fromMoneyAccountId");
        final String toMoneyCardNumber = request.getParameter("toMoneyCardNumber");
        final String moneyAmount = request.getParameter("moneyAmount");
        final User user = (User)request.getSession().getAttribute("user");
        try {
            MoneyCardService moneyCardService = new MoneyCardService(user);

        } catch (DBException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
