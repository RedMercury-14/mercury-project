package by.myproject.main.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.myproject.main.controller.Command;
import by.myproject.main.service.ServiceException;

public class GoToAddCarPage implements Command{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		request.getRequestDispatcher("/WEB-INF/jsp/addCar.jsp").forward(request, response);
		
	}

}
