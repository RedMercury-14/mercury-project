package by.myproject.main.controller.command;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.Order;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;
import by.myproject.main.util.DateValidator;

public class OrderCommand implements Command {
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
	private static final Logger log = Logger.getLogger(OrderCommand.class);

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		response.setContentType("text/html");
		String command = request.getParameter("command");
		HttpSession session = request.getSession();

		if ("GO_TO_ADDORDER_PAGE".equals(command)) {
			response.sendRedirect("MainController?command=GO_TO_ADDORDER_PAGE");
		} else if (command.equals("addOrder")) {
			try {
				String route = request.getParameter("route");
				String ordCol = request.getParameter("col");
				String dateLoad = request.getParameter("datel");
				String dateCust = request.getParameter("datec");
				String dateUnload = request.getParameter("dateun");
				String prise = request.getParameter("price");
				String cargo = request.getParameter("cargo");
				String client = request.getParameter("client");
				String user = (String) session.getAttribute("login");
				DateValidator dateServise = new DateValidator();
				if (!dateServise.isValidationDate(dateLoad) || !dateServise.isValidationDate(dateCust)
						|| !dateServise.isValidationDate(dateUnload)) {
					response.sendRedirect("MainController?command=GO_TO_ADDORDER_PAGE&errorMessage="
							+ URLEncoder.encode("Неправильный формат даты", "utf-8"));
				} else {
					Order order = new Order(route, ordCol, dateLoad, dateCust, dateUnload, client, prise, cargo, user);
					serviceFactory.getOrderService().setOrder(order);
					response.sendRedirect("MainController?command=GO_TO_ORDERLIST_PAGE");
				}

			} catch (Exception ex) {
				log.error("Ошибка отправки формы", ex);
				response.sendRedirect("MainController?command=GO_TO_ERROR_PAGE&errorMessage = Ошибка отправки формы!");
			}
		} else if (command.equals("editOrder")) {
			try {
				int numOrd = Integer.parseInt(request.getParameter("numord"));
				String route = request.getParameter("route");
				String ordCol = null;
				String dateLoad = request.getParameter("datel");
				String dateCust = request.getParameter("datec");
				String dateUnload = request.getParameter("dateun");
				String prise = request.getParameter("price");
				String cargo = request.getParameter("cargo");
				String client = request.getParameter("client");
				String user = (String) session.getAttribute("login");
				DateValidator dateServise = new DateValidator();
				if (!dateServise.isSQLValidationDate(dateLoad) || !dateServise.isSQLValidationDate(dateCust)
						|| !dateServise.isSQLValidationDate(dateUnload)) {
					if (!dateServise.isValidationDate(dateLoad) || !dateServise.isValidationDate(dateCust)
							|| !dateServise.isValidationDate(dateUnload)) {
						Order order = serviceFactory.getOrderService().getOrder(numOrd);
						request.setAttribute("Order", order);
						request.setAttribute("errorMessageOrder", "Неправильный формат даты");
						request.getRequestDispatcher("/WEB-INF/jsp/editOrder.jsp").forward(request, response);
					} else {
						Order order = new Order(numOrd, route, ordCol, dateLoad, dateCust, dateUnload, client, prise,
								cargo, user); // это слой сервисов
						serviceFactory.getOrderService().updateOrder(order);
						response.sendRedirect("MainController?command=GO_TO_ORDERLIST_PAGE");
					}
				} else {
					Order order = new Order(numOrd, route, ordCol, dateLoad, dateCust, dateUnload, client, prise, cargo,
							user);
					serviceFactory.getOrderService().updateOrder(order);
					response.sendRedirect("MainController?command=GO_TO_ORDERLIST_PAGE");
				}
			} catch (Exception ex) {
				log.error("Ошибка отправки формы", ex);
				response.sendRedirect("MainController?command=GO_TO_ERROR_PAGE&errorMessage = Ошибка отправки формы!");
			}
		} else if ("delOrder".equals(command)) {
			try {
				int numOrd = Integer.parseInt(request.getParameter("numord"));
				serviceFactory.getOrderService().deleteOrder(numOrd);
				response.sendRedirect("MainController?command=GO_TO_ORDERLIST_PAGE");
			} catch (Exception ex) {
				log.error("Ошибка отправки формы", ex);
				response.sendRedirect("MainController?command=GO_TO_ERROR_PAGE&errorMessage = Ошибка отправки формы!");
			}
		} else if ("editCarInOrder".equals(command)) {
			Integer numOrd = Integer.parseInt(request.getParameter("numord"));
			String option = request.getParameter("isTruck");
			Order order = serviceFactory.getOrderService().getOrder(numOrd);
			order.setCarNum(option);
			serviceFactory.getOrderService().updateOrder(order);
			response.sendRedirect("MainController?command=GO_TO_ORDERLIST_PAGE");
		} else if ("proof".equals(command)) {
			Integer numOrd = Integer.parseInt(request.getParameter("numord"));
			String option = request.getParameter("accept");
			Order order = serviceFactory.getOrderService().getOrder(numOrd);
			String login = (String) session.getAttribute("login");
			order.setAdmin(login);
			order.setStatus(option);
			serviceFactory.getOrderService().updateOrder(order);
			response.sendRedirect("MainController?command=GO_TO_ORDERLIST_PAGE");
		}
	}
}
