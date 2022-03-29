package by.myproject.main.controller.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.WorkerHasTruck;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;


public class GoToWayBillPage implements Command {

	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
	

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException{
		List<WorkerHasTruck> workerHasTruckArray =  serviceFactory.getWorkerHasTruckService().getWorkerHasTruck();
		request.setAttribute("WorkerHasTruck", workerHasTruckArray);		
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/waybillPage.jsp");
		dispatcher.forward(request, response);
	}
}
