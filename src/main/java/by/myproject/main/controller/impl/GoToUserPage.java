package by.myproject.main.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.Truck;
import by.myproject.main.service.ServiceException;

public class GoToUserPage implements Command{

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userPage.jsp");
		dispatcher.forward(request, response);
	}

}
