package com.kerrrusha.lab234.filter;

import com.kerrrusha.lab234.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebFilter(value = "/is-authorized-filter")
public class IsAuthorizedFilter implements Filter {

	private static final Logger logger = Logger.getLogger(IsAuthorizedFilter.class);

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
			logger.info("New authorized request from user.id = " + user.getId());
			chain.doFilter(request, response);
		} else {
			logger.info("New non-authorized request");
			httpServletResponse.sendRedirect(request.getServletContext().getContextPath() + "/signup");
		}
	}
}
