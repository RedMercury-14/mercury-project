package by.myproject.main.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.myproject.main.controller.Command;
import by.myproject.main.service.ServiceException;

public class Exit implements Command{


	public void process(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException{
		HttpSession session = request.getSession();
		session.setAttribute("id", null);
		session.setAttribute("name", null);
		session.setAttribute("surname", null);
		session.setAttribute("patronymic", null);
		session.setAttribute("numpass", null);
		session.setAttribute("position", null);
		session.setAttribute("email", null);
		session.setAttribute("password", null);
		session.setAttribute("login", null);
		session.setAttribute("role", "Guest");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp");
		dispatcher.forward(request, response);	
		request.getSession(true);
	}

}
