package com.kerrrusha.lab234.controller;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.service.money.MoneyService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/moneycards")
public class MoneycardsServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("moneycards.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            User user = (User)request.getSession().getAttribute("user");
            MoneyService moneyService = new MoneyService(user);
            final String json = moneyService.getUserMoneycardsViewModelJson();
            response.setContentType("application/json");
            response.getWriter().print(json);
        } catch (DBException e) {
            logger.error(e.getMessage());
        }
    }
}
