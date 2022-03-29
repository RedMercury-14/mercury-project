package by.myproject.main.controller.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.Order;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;

public class CommentDispositionCommand implements Command {
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
	private static final Logger log = Logger.getLogger(DispositionCommand.class);

	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
		String comment = request.getParameter("comment");
		if (comment.isEmpty()) {
			response.sendRedirect("MainController?command=GO_TO_DISPOSITION_PAGE");
		}else {
			int numOrd = Integer.parseInt(request.getParameter("numord"));
			Order order = serviceFactory.getOrderService().getOrder(numOrd);					
			order.setOrdCol(comment);
			serviceFactory.getOrderService().updateOrder(order);
			response.sendRedirect("MainController?command=GO_TO_DISPOSITION_PAGE");
		}
		
	}

}
