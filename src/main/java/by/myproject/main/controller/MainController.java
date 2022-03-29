package by.myproject.main.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(MainController.class);
	private final CommandProvider commandProvider = new CommandProvider();

	public MainController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		execut(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		execut(request, response);
	}
	private void execut (HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			String user = (String) session.getAttribute("role");
			if (user == null) {
				session.setAttribute("role", "Guest");
				session.setAttribute("userName", null);
				String commandName = request.getParameter("command");
				Command command = commandProvider.getCommand(commandName);
				command.process(request, response);
			} else {
				String commandName = request.getParameter("command");
				Command command = commandProvider.getCommand(commandName);
				command.process(request, response);
			}
		} catch (Throwable e) {
			log.error("Controller operation error", e);
		}
	}

}
