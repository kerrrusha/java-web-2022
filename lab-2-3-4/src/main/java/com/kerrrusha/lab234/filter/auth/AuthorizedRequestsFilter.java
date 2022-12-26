package com.kerrrusha.lab234.filter.auth;

import com.kerrrusha.lab234.model.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/signin", "/signup"}, description = "Redirect all authorized requests to signup.jsp")
public class AuthorizedRequestsFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AuthorizedRequestsFilter.class);

    public void init(FilterConfig config) {}

    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);

        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        if (loggedIn) {
            User user = (User) session.getAttribute("user");
            logger.info("Authorized request to auth from user.id = " + user.getId());
            httpServletResponse.sendRedirect(request.getServletContext().getContextPath());
        } else {
            logger.info("Non-authorized request to auth");
            chain.doFilter(request, response);
        }
    }
}
