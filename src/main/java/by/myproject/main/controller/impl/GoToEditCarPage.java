package by.myproject.main.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.Truck;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;

public class GoToEditCarPage implements Command{
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
	private static final Logger log = Logger.getLogger(GoToEditCarPage.class);

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		try {
			String carNum = request.getParameter("carnum");			
			Truck truck = serviceFactory.getTruckService().getTruck(carNum);
			if (truck != null) {
				request.setAttribute("Truck", truck);
				request.getRequestDispatcher("/WEB-INF/jsp/editCar.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} catch (Exception ex) {
			log.error("Form submission error",ex);
			request.setAttribute("errorMessage", "Form submission error");
			System.out.println(ex);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
	}

}
