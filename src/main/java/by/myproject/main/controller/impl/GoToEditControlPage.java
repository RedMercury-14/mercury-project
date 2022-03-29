package by.myproject.main.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.Role;
import by.myproject.main.entity.UserControl;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;

public class GoToEditControlPage implements Command{
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		int id = Integer.parseInt(request.getParameter("num").trim());
		UserControl userControl = serviceFactory.getUserControlService().getUserControl(id);
		request.setAttribute("UserControl", userControl);
		List<Role> roleArray = serviceFactory.getUserService().getRoleList();
		request.setAttribute("Role", roleArray);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/editControlPage.jsp");
		dispatcher.forward(request, response);
		
	}

}
