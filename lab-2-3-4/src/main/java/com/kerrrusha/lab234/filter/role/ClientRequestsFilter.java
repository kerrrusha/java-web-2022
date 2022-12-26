package com.kerrrusha.lab234.filter.role;

import com.kerrrusha.lab234.model.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {
        "/admin-panel"
})
public class ClientRequestsFilter implements Filter {

    private static final Logger logger = Logger.getLogger(ClientRequestsFilter.class);
    private static final int ADMIN_ROLE_ID = 2;

    public void init(FilterConfig config) {}

    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);

        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        if (!loggedIn) {
            logger.info("Non-authorized request to admin-panel");
            httpServletResponse.sendRedirect(request.getServletContext().getContextPath() + "/signin");
        } else {
            User user = (User) session.getAttribute("user");
            if (user.getRoleId() != ADMIN_ROLE_ID) {
                logger.info("Non-admin request to admin-panel");
                httpServletResponse.sendRedirect(request.getServletContext().getContextPath());
                return;
            }
            chain.doFilter(request, response);
        }
    }
}
