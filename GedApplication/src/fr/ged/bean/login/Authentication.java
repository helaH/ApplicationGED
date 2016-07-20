package fr.ged.bean.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authentication implements Filter {
	protected ServletContext servletContext;
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (req.getSession().getAttribute(Login.AUTH_KEY) == null
				|| checkSession(req)) {
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) {
		servletContext = filterConfig.getServletContext();
	}

	public void destroy() {
		servletContext = null;
	}

	private boolean checkSession(HttpServletRequest request) {
		return request.getRequestedSessionId() != null
				&& !request.isRequestedSessionIdValid();

	}

}
