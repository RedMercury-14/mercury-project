package by.myproject.main.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BlockFilter implements Filter {
	private FilterConfig filterConfig;

	/**
	 * Default constructor.
	 */
	public BlockFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if (session.getAttribute("role") == null) {
			chain.doFilter(request, response);
			return;
		}
		if (session.getAttribute("role").equals("block") && !req.getParameter("command").equals("EXIT")) {
			session.setAttribute("errorMessage", "Аккаунт заблокирован! Обратитесь к администратору.");
			ServletContext ctx = filterConfig.getServletContext();
			RequestDispatcher dispatcher = ctx.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		}else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {		
		filterConfig = fConfig;
	}

}