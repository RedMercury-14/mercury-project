package by.myproject.main.controller.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.myproject.main.controller.Command;
import by.myproject.main.service.ServiceException;

public class DispositionCommand implements Command {
	public DispositionCommand() {
		super();
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {		
		response.sendRedirect("MainController?command=GO_TO_DISPOSITION_PAGE");
	}
	

}
