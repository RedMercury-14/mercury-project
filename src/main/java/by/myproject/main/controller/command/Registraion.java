package by.myproject.main.controller.command;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.User;
import by.myproject.main.entity.Worker;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;
import by.myproject.main.util.IDGenerate;

public class Registraion implements Command {
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
	private String name;
	private String surname;
	private String patronymic;
	private String password;
	private String password2;
	private String login;

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		this.name = request.getParameter("name");
		this.surname = request.getParameter("surname");
		this.patronymic = request.getParameter("patronymic");
		this.password = request.getParameter("password");
		this.password2 = request.getParameter("password2");
		this.login = request.getParameter("login");		
		
		if (name.isEmpty() || surname.isEmpty() || password.isEmpty()) {	
			response.sendRedirect("MainController?command=GO_TO_REGISTRATION_PAGE&errorMessage="+URLEncoder.encode("Не заполнены все обязательные поля!","utf-8"));
		}else if (password.equals(password2) != true) {
			response.sendRedirect("MainController?command=GO_TO_REGISTRATION_PAGE&errorMessage="+URLEncoder.encode("Пароли не совпадают!","utf-8"));						
		}else {	
			String tabelNumber = ""+IDGenerate.getNewID();
			boolean flag = serviceFactory.getUserService().isContain(login);
			if (flag == true) {
				response.sendRedirect("MainController?command=GO_TO_REGISTRATION_PAGE&errorMessage="+URLEncoder.encode("Пользователь с таким логином существует!","utf-8"));	
			}else {
				User user = new User(login, 3, tabelNumber, password);				
				Worker worker = new Worker(tabelNumber, login, name, surname, patronymic, null, null, null);
				serviceFactory.getUserService().setUser(user);
				serviceFactory.getWorkerService().setWorker(worker);	
				response.sendRedirect("MainController?command=GO_TO_WELCOME_PAGE");		
			}				
			}
		
	}}
