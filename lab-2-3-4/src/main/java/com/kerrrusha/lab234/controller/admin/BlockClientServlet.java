package com.kerrrusha.lab234.controller.admin;

import com.kerrrusha.lab234.dao.DBException;
import com.kerrrusha.lab234.factory.ResultSenderFactory;
import com.kerrrusha.lab234.model.User;
import com.kerrrusha.lab234.service.block_client.BlockClientService;
import com.kerrrusha.lab234.service.block_client.result.BlockClientResult;
import com.kerrrusha.lab234.service.block_client.result.BlockClientResultSender;
import org.apache.http.HttpStatus;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/block-client")
public class BlockClientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String clientIdStr = request.getParameter("clientId");
        final User user = (User)request.getSession().getAttribute("user");

        BlockClientService blockClientService;
        try {
            blockClientService = new BlockClientService(user);
        } catch (DBException e) {
            ResultSenderFactory.createAbstractResultSenderFromError(e.getMessage(), HttpStatus.SC_INTERNAL_SERVER_ERROR).sendResponse(response);
            return;
        }
        BlockClientResult result = blockClientService.blockClientAsAdmin(clientIdStr);
        BlockClientResultSender.valueOf(result).sendResponse(response);
    }
}
