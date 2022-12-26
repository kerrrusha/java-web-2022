package com.kerrrusha.lab234.controller.admin;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.factory.ResultSenderFactory;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.service.block_money_account.BlockMoneyAccountService;
import com.kerrrusha.lab234.service.block_money_account.result.BlockMoneyAccountResult;
import com.kerrrusha.lab234.service.block_money_account.result.BlockMoneyAccountResultSender;
import org.apache.http.HttpStatus;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/unblock-money-account")
public class UnblockMoneyAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String moneyAccountIdStr = request.getParameter("moneyAccountId");
        final User user = (User)request.getSession().getAttribute("user");

        BlockMoneyAccountService blockMoneyAccountService;
        try {
            blockMoneyAccountService = new BlockMoneyAccountService(user);
        } catch (DBException e) {
            ResultSenderFactory.createAbstractResultSenderFromError(e.getMessage(), HttpStatus.SC_INTERNAL_SERVER_ERROR).sendResponse(response);
            return;
        }
        BlockMoneyAccountResult result = blockMoneyAccountService.unblockMoneyAccountAsAdmin(moneyAccountIdStr);
        BlockMoneyAccountResultSender.valueOf(result).sendResponse(response);
    }
}
