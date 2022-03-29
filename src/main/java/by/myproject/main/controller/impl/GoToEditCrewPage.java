package by.myproject.main.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.myproject.main.controller.Command;
import by.myproject.main.controller.command.WorkerHasTruckCommand;
import by.myproject.main.entity.Truck;
import by.myproject.main.entity.Worker;
import by.myproject.main.entity.WorkerHasTruck;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;

public class GoToEditCrewPage implements Command{

	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
	private static final Logger log = Logger.getLogger(GoToEditCrewPage.class);

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		response.setContentType("text/html");
		List<Worker> driversList = new ArrayList<Worker>();
		try {
			Integer id = Integer.parseInt(request.getParameter("numid"));
			WorkerHasTruck workerHasTruck = serviceFactory.getWorkerHasTruckService().getWorkerHasTruck(id);
			if (workerHasTruck != null) {
				driversList = getDriver();
				request.setAttribute("Worker", driversList);
				List<Truck> truck = serviceFactory.getTruckService().getTruckList();
				request.setAttribute("Truck", truck);
				request.setAttribute("WorkerHasTruck", workerHasTruck);
				request.getRequestDispatcher("/WEB-INF/jsp/editCrew.jsp").forward(request, response);
			} else {
				log.error("Ошибка получения объекта workerHasTruck");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} catch (Exception ex) {
			log.error("Ошибка обработки контроллера экипажей", ex);
			request.setAttribute("errorMessage", "Ошибка обработки контроллера экипажей");
			request.getRequestDispatcher("/error.jsp").forward(request, response);

		}
		
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
			throw new ServiceException(e); //what?
		}
	}

}
