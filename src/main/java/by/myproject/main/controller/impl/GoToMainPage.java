package by.myproject.main.controller.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.myproject.main.controller.Command;
import by.myproject.main.service.ServiceException;

public class GoToMainPage implements Command {


	public void process(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException{
		String path = request.getContextPath();		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp");
		dispatcher.forward(request, response);	
		HttpSession session = request.getSession();
		session.removeAttribute("error");
	}

}
