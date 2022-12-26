package com.kerrrusha.lab234.controller;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.exception.BlockMoneyAccountException;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.model.MoneyCard;
import com.kerrrusha.lab234.service.BlockMoneyAccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/block-money-account")
public class BlockMoneyAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String message;
        try {
            final String moneyAccountIdStr = request.getParameter("moneyAccountId");
            User user = (User)request.getSession().getAttribute("user");
            MoneyCard blockedMoneyCard = new BlockMoneyAccountService(user).blockMoneyAccount(moneyAccountIdStr);
            message = "Your money account (" + blockedMoneyCard.getPrettyBalanceString() + ") was blocked successfully.";
        } catch (BlockMoneyAccountException | NumberFormatException | DBException e) {
            message = e.getMessage();
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("block-money-account.jsp").forward(request, response);
    }
}
