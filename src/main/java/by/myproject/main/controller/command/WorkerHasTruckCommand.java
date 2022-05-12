package by.myproject.main.controller.command;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.Truck;
import by.myproject.main.entity.Worker;
import by.myproject.main.entity.WorkerHasTruck;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;
import by.myproject.main.util.DateValidator;

public class WorkerHasTruckCommand implements Command {
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
	private static final Logger log = Logger.getLogger(WorkerHasTruckCommand.class);

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
		response.setContentType("text/html");
		String message = request.getParameter("command");
		if ("GO_TO_NEWCREW_PAGE".equals(message)) {
			response.sendRedirect("MainController?command=GO_TO_NEWCREW_PAGE");
		}else if ("newCrew".equals(request.getParameter("command"))) {
			try {
				String workerStr = request.getParameter("isWorker").replaceAll("\\s+", "");
				String[] userParseString = workerStr.split("[,=]");
				int idWorkers = Integer.parseInt(userParseString[1].trim());
				String trucksNumber = request.getParameter("isTruck");
				String dateOfStart = request.getParameter("dates");
				String dateOfFinish = request.getParameter("datef");
				String workersPosition = userParseString[7].concat(" " + userParseString[5]); // или доставать из БД?
				DateValidator dateServise = new DateValidator();
				if (dateOfFinish == null) {
					dateOfFinish = null;
				}
				if (!dateServise.isValidationDate(dateOfStart)) {
					response.sendRedirect("MainController?command=GO_TO_NEWCREW_PAGE&errorMessage="+URLEncoder.encode("Incorrect date format!","utf-8"));
				} else {
					WorkerHasTruck workerHasTruck = new WorkerHasTruck(idWorkers, trucksNumber, workersPosition,
							dateOfStart, dateOfFinish);
					serviceFactory.getWorkerHasTruckService().setWorkerHasTruck(workerHasTruck);
					response.sendRedirect("MainController?command=GO_TO_WAYBILL_PAGE");
				}

			} catch (Exception ex) {
				log.error("Form submission error!", ex);
				response.sendRedirect("MainController?command=GO_TO_ERROR_PAGE&errorMessage = Form submission error!");
			}
		} else if ("editCrew".equals(request.getParameter("command"))) {
			try {
				String workerStr = request.getParameter("isWorker").replaceAll("\\s+", "");
				String[] userParseString = workerStr.split("[,=]");
				int id = Integer.parseInt(request.getParameter("crewnum").trim());
				int idWorkers = Integer.parseInt(userParseString[1].trim());
				String trucksNumber = request.getParameter("isTruck");
				String dateOfStart = request.getParameter("dates");
				String dateOfFinish = request.getParameter("datef");
				String workersPosition = userParseString[7].concat(" " + userParseString[5]);
				DateValidator dateServise = new DateValidator();
				if (dateOfFinish == null) {
					dateOfFinish = null;
				}
				if (!dateServise.isSQLValidationDate(dateOfStart)) {
					List<Worker> driversList1 = new ArrayList<Worker>();
					WorkerHasTruck workerHasTruck = serviceFactory.getWorkerHasTruckService().getWorkerHasTruck(id);
					request.setAttribute("errorMessageWaybill", "Incorrect date format");
					driversList1 = getDriver();
					request.setAttribute("Worker", driversList1);
					List<Truck> truck = serviceFactory.getTruckService().getTruckList();
					request.setAttribute("Truck", truck);
					request.setAttribute("WorkerHasTruck", workerHasTruck);
					request.getRequestDispatcher("/WEB-INF/jsp/editCrew.jsp").forward(request, response);
				} else {
					WorkerHasTruck workerHasTruck = new WorkerHasTruck(id, idWorkers, trucksNumber, workersPosition,
							dateOfStart, dateOfFinish);
					serviceFactory.getWorkerHasTruckService().updateWorkerHasTruck(workerHasTruck);
					response.sendRedirect("MainController?command=GO_TO_WAYBILL_PAGE");
				}
			} catch (Exception ex) {
				log.error("Form submission error!", ex);
				response.sendRedirect("MainController?command=GO_TO_ERROR_PAGE&errorMessage = Form submission error!");
			}
		} else if ("delCrew".equals(request.getParameter("command"))) {
			try {
				int numOrd = Integer.parseInt(request.getParameter("numord"));
				serviceFactory.getWorkerHasTruckService().deleteWorkerHasTruck(numOrd);
				response.sendRedirect("MainController?command=GO_TO_WAYBILL_PAGE");
			} catch (Exception ex) {
				log.error("Ошибка отправки формы", ex);
				response.sendRedirect("MainController?command=GO_TO_ERROR_PAGE&errorMessage = Form submission error!");
			}
		} else {
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
