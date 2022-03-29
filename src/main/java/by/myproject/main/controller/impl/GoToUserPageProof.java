package by.myproject.main.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.myproject.main.controller.Command;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;

public class GoToUserPageProof implements Command {
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException{
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
				
		if(serviceFactory.getWorkerService().isUser(login, role)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userPage.jsp");			
			dispatcher.forward(request, response);
		}else {
			session.setAttribute("errorMessage", "Неизвестный пользователь! Выполните вход");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/logination.jsp");
			dispatcher.forward(request, response);
		}	
	}

}
