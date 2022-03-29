package by.myproject.main.controller.command;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.User;
import by.myproject.main.entity.Worker;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;

public class Setting implements Command{
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
	private String login;
	
	public void process (HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
		HttpSession session = request.getSession();	
		
		this.login = (String) session.getAttribute("login");
		User user = serviceFactory.getUserService().getUser(login);
		int id = Integer.parseInt(user.getTabelNumber());
		Worker worker = serviceFactory.getWorkerService().getWorker(id);
		worker.setName(request.getParameter("name"));
		worker.setNumberOfPassport(request.getParameter("numpass"));
		user.setPassword(request.getParameter("password"));
		worker.setPatronymic(request.getParameter("patronymic"));
		worker.setSurName(request.getParameter("surname"));
		worker.seteMail(request.getParameter("email"));
		
		session.setAttribute("id", worker.getId());
		session.setAttribute("name", worker.getName());
		session.setAttribute("surname", worker.getSurName());
		session.setAttribute("patronymic", worker.getPatronymic());
		session.setAttribute("numpass", worker.getNumberOfPassport());
		session.setAttribute("position", worker.getPosition());
		session.setAttribute("email", worker.geteMail());
		session.setAttribute("password", user.getPassword());
		session.setAttribute("login", login);
		response.sendRedirect("MainController?command=GO_TO_SETTING_PAGE&errorMessage=Настройки сохранены!");
	}

}
