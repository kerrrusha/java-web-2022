package com.kerrrusha.lab234.controller;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.exception.RestrictedMoneyAccountException;
import com.kerrrusha.lab234.factory.ResultSenderFactory;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.service.money.MoneyService;
import com.kerrrusha.lab234.service.money.result.billing.BillingResult;
import com.kerrrusha.lab234.service.money.result.billing.BillingResultSender;
import com.kerrrusha.lab234.validator.MoneyAccountValidator;
import org.apache.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/create-replenishment")
public class CreateReplenishmentServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final String toMoneyAccountIdStr = request.getParameter("toMoneyAccountId");
        final User user = (User)request.getSession().getAttribute("user");

        try {
            int toMoneyAccountId = Integer.parseInt(toMoneyAccountIdStr);
            Collection<String> errorPool = new MoneyAccountValidator(user, toMoneyAccountId).getErrors();
            if (!errorPool.isEmpty()) {
                throw new RestrictedMoneyAccountException(errorPool);
            }
            MoneyService moneyService = new MoneyService(user);

            request.setAttribute("moneyAccount", moneyService.getMoneyAccountById(toMoneyAccountId));
            request.setAttribute("moneyCard", moneyService.getMoneyCardByMoneyAccountId(toMoneyAccountId));
        } catch (NumberFormatException | DBException | RestrictedMoneyAccountException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("create-replenishment.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String toMoneyAccountIdStr = request.getParameter("toMoneyAccountId");
        final String fromMoneyCardNumber = request.getParameter("fromMoneyCardNumber");
        final String fromMoneyCardSecret = request.getParameter("fromMoneyCardSecret");
        final String moneyAmountStr = request.getParameter("moneyAmount");
        final User user = (User)request.getSession().getAttribute("user");

        MoneyService moneyService;
        try {
            moneyService = new MoneyService(user);
        } catch (DBException e) {
            ResultSenderFactory.createAbstractResultSenderFromError(e.getMessage(), HttpStatus.SC_INTERNAL_SERVER_ERROR).sendResponse(response);
            return;
        }
        BillingResult result = moneyService.replenishMoney(toMoneyAccountIdStr, fromMoneyCardNumber, fromMoneyCardSecret, moneyAmountStr);
        BillingResultSender.valueOf(result).sendResponse(response);
    }
}
