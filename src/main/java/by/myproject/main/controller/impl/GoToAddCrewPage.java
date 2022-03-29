package by.myproject.main.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.Truck;
import by.myproject.main.entity.Worker;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;

public class GoToAddCrewPage implements Command{
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		response.setContentType("text/html");
		List<Worker> driversList = new ArrayList<Worker>();
		driversList = getDriver();
		request.setAttribute("Worker", driversList);
		List<Truck> truck = serviceFactory.getTruckService().getTruckList();
		request.setAttribute("Truck", truck);
		request.getRequestDispatcher("/WEB-INF/jsp/addCrew.jsp").forward(request, response);
		
	}
	private List<Worker> getDriver() throws ServiceException {
		List<Worker> driversList = new ArrayList<Worker>();
		List<Worker> worker;
		try {
			worker = serviceFactory.getWorkerService().getWorkerList();
			for (Worker driver : worker) {
				if (driver.getPosition() != null && driver.getPosition().equals("Driver")) {
					driversList.add(driver);
				}
			}
			return driversList;
		} catch (ServiceException e) {
			throw new ServiceException(e); 
		}
	}

}
