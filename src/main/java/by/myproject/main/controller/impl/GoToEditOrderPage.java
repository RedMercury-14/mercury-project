package by.myproject.main.controller.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.Order;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;

public class GoToEditOrderPage implements Command{
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
	private static final Logger log = Logger.getLogger(GoToEditOrderPage.class);

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		try {
			Integer numOrd = Integer.parseInt(request.getParameter("numord"));				
			Order order = serviceFactory.getOrderService().getOrder(numOrd); 
			if (order != null) {
				request.setAttribute("Order", order);
				request.getRequestDispatcher("/WEB-INF/jsp/editOrder.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} catch (Exception ex) {
			log.error("Order Controller Processing error",ex);
			request.setAttribute("errorMessage", "Order Controller Processing error");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		}
		request.getRequestDispatcher("/WEB-INF/jsp/editOrder.jsp").forward(request, response);		
	}

}
