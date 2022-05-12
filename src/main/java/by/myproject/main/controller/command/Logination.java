package by.myproject.main.controller.command;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.User;
import by.myproject.main.entity.Worker;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;

public class Logination implements Command {
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
		String login;
		String password;
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");		
		login = request.getParameter("login");
		password = request.getParameter("password");
		
		
		if(login.isEmpty() || password.isEmpty()) {	
			response.sendRedirect("MainController?command=GO_TO_LOGINATION_PAGE&errorMessage="+URLEncoder.encode("All required fields are not filled in","utf-8"));
			
		}else if(serviceFactory.getUserService().isUser(login, password)){
			response.sendRedirect("MainController?command=GO_TO_USER_PAGE");
			HttpSession session = request.getSession();
			User user = serviceFactory.getUserService().getUser(login);
			int id = Integer.parseInt(user.getTabelNumber());
			Worker worker = serviceFactory.getWorkerService().getWorker(id);
			session.setAttribute("id", id);
			session.setAttribute("name", worker.getName());
			session.setAttribute("surname", worker.getSurName());
			session.setAttribute("patronymic", worker.getPatronymic());
			session.setAttribute("numpass", worker.getNumberOfPassport());
			session.setAttribute("position", worker.getPosition());
			session.setAttribute("email", worker.geteMail());
			session.setAttribute("password", user.getPassword());
			session.setAttribute("login", login);
			session.setAttribute("role", serviceFactory.getWorkerService().getRole(login));
			response.setContentType("text/html;charset=UTF-8");			
		}else {
			response.sendRedirect("MainController?command=GO_TO_LOGINATION_PAGE&errorMessage="+URLEncoder.encode("Incorrect login or password","utf-8"));
		}
	}

}