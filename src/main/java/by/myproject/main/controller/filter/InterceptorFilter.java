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

import org.apache.log4j.Logger;

public class InterceptorFilter implements Filter {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(InterceptorFilter.class);

	private FilterConfig filterConfig;
	public InterceptorFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");

		String command = request.getParameter("command");
		if (command == null) {
			chain.doFilter(request, response);
			return;
		}

		if (command.equals("GO_TO_USER_PAGE")) {

			if (login != null && role != null) {
				chain.doFilter(request, response);
				return;
			} else {
				session.setAttribute("errorMessage", "Unknown user! Log in");
				ServletContext ctx = filterConfig.getServletContext();
				RequestDispatcher dispatcher = ctx.getRequestDispatcher("/WEB-INF/jsp/logination.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}		
		chain.doFilter(request, response);

	}

	public void init(FilterConfig fConfig) throws ServletException {
		filterConfig = fConfig;
	}

}
