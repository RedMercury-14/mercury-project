package by.myproject.main.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.UserControl;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;

public class GoToControlPage implements Command{
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		List<UserControl> userControllArray = serviceFactory.getUserControlService().getUserControlList();
		request.setAttribute("UserControllArray", userControllArray);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/controlPage.jsp");
		dispatcher.forward(request, response);
		
	}

}
