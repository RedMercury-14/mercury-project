package by.myproject.main.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.Order;
import by.myproject.main.entity.Truck;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;

public class GoToOrderListPage implements Command {
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException{
		List<Order> order =  serviceFactory.getOrderService().getOrderList();			
		request.setAttribute("Order", order);
		
		List<Truck> truck = serviceFactory.getTruckService().getTruckList();
		request.setAttribute("Truck", truck);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/orderListPage.jsp");
		dispatcher.forward(request, response);
	}
	

}
