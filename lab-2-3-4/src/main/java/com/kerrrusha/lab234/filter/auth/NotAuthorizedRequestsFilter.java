package com.kerrrusha.lab234.filter.auth;

import com.kerrrusha.lab234.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import java.io.IOException;

@WebFilter(urlPatterns = {
		"/",
		"/about",
		"/cabinet",
		"/open-new-money-card",
		"/moneycards",
		"/create-billing",
		"/billings",
		"/create-replenishment",
		"/block-money-account",
		"/admin/panel"
}, description = "Redirect all not authorized requests to /signin")
public class NotAuthorizedRequestsFilter implements Filter {

	private static final Logger logger = Logger.getLogger(NotAuthorizedRequestsFilter.class);

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
			logger.info("Authorized request to index from user.id = " + user.getId());
			chain.doFilter(request, response);
		} else {
			logger.info("Non-authorized request to index");
			httpServletResponse.sendRedirect(request.getServletContext().getContextPath() + "/signin");
		}
	}
}
