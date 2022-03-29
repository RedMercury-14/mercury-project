package by.myproject.main.controller.command;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.Truck;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;
import by.myproject.main.service.valid.NotDataFieldException;
import by.myproject.main.service.valid.NotDateFormatException;
import by.myproject.main.util.DateValidator;

public class TruckCommand implements Command {
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
	private static final Logger log = Logger.getLogger(TruckCommand.class);

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		response.setContentType("text/html");
		String command = request.getParameter("command");

		if ("GO_TO_ADDTRUCK_PAGE".equals(command)) {
			response.sendRedirect("MainController?command=GO_TO_ADDTRUCK_PAGE");
		} else if ("addTruck".equals(command)) {
			addTruck(request, response);
		} else if ("editTruck".equals(command)) {
			editTruck(request, response);
		} else if ("delTruck".equals(command)) {
			delTrack(request, response);
		}
	}

	private void addTruck(HttpServletRequest request, HttpServletResponse response)
			throws ServiceException, ServletException, IOException {
		try {
			String numCar = request.getParameter("carnum");
			String numTr = request.getParameter("trnum");
			String carModel = request.getParameter("carmod");
			String trModel = request.getParameter("trmod");
			String typeTr = request.getParameter("typetr");
			String dateCar = request.getParameter("datecar");
			String dateTr = request.getParameter("datetr");
			Truck truck = new Truck(numCar, numTr, carModel, trModel, typeTr, dateCar, dateTr);
			serviceFactory.getTruckService().setTruck(truck);
			response.sendRedirect("MainController?command=GO_TO_TRUCKLIST_PAGE");

		} catch (ServiceException ex) {
			log.error("Ошибка отправки формы", ex);
			request.setAttribute("errorMessage", "Ошибка отправки формы");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		} catch (NotDateFormatException e) {
			log.error("Неправильный формат даты", e);
			response.sendRedirect("MainController?command=GO_TO_ADDTRUCK_PAGE&errorMessageTruck="
					+ URLEncoder.encode("Неправильный формат даты", "utf-8"));
		} catch (NotDataFieldException e) {
			log.error("Не заполнены все поля", e);
			response.sendRedirect("MainController?command=GO_TO_ADDTRUCK_PAGE&errorMessageTruck="
					+ URLEncoder.encode("Не заполнены все поля", "utf-8"));
		}
	}

	private void editTruck(HttpServletRequest request, HttpServletResponse response)
			throws ServiceException, ServletException, IOException {
		try {
			String numCar = request.getParameter("carnum");
			String numTr = request.getParameter("trnum");
			String carModel = request.getParameter("carmod");
			String trModel = request.getParameter("trmod");
			String typeTr = request.getParameter("typetr");
			String dateCar = request.getParameter("datecar");
			String dateTr = request.getParameter("datetr");
			DateValidator dateServise = new DateValidator();
			if (!dateServise.isSQLValidationDate(dateCar) || !dateServise.isSQLValidationDate(dateTr)) {
				Truck truck = serviceFactory.getTruckService().getTruck(numCar);
				;
				request.setAttribute("Truck", truck);
				request.setAttribute("errorMessageTruck", "Неправильный формат даты");
				request.getRequestDispatcher("/WEB-INF/jsp/editCar.jsp").forward(request, response);
			} else {
				Truck truck = new Truck(numCar, numTr, carModel, trModel, typeTr, dateCar, dateTr);
				serviceFactory.getTruckService().updateTruck(truck);
				response.sendRedirect("MainController?command=GO_TO_TRUCKLIST_PAGE");
			}
		} catch (Exception ex) {
			log.error("Ошибка отправки формы", ex);
			response.sendRedirect("MainController?command=GO_TO_ERROR_PAGE&errorMessage = Ошибка отправки формы!");
		}
	}

	private void delTrack(HttpServletRequest request, HttpServletResponse response)
			throws ServiceException, ServletException, IOException {
		try {
			String carNum = request.getParameter("numcar");
			serviceFactory.getTruckService().deleteTruck(carNum);
			response.sendRedirect("MainController?command=GO_TO_TRUCKLIST_PAGE");
		} catch (ServiceException ex) {
			log.error("Ошибка отправки формы", ex);
			request.getSession().removeAttribute("errorMessage"); //временно! 
			response.sendRedirect("MainController?command=GO_TO_ERROR_PAGE&errorMessage="
					+ URLEncoder.encode("Невозможно удалить авто, т.к. машина привязана к путевому листу!", "utf-8"));
		}
	}

}