package by.myproject.main.controller.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.myproject.main.controller.Command;
import by.myproject.main.entity.User;
import by.myproject.main.entity.UserControl;
import by.myproject.main.entity.Worker;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;
import by.myproject.main.util.IDGenerate;

public class ControlCommand implements Command {
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
		if ("GO_TO_ADDCONTROLL_PAGE".equals(request.getParameter("command"))) {
			response.sendRedirect("MainController?command=GO_TO_REGISTRATION_PAGE");
		}else if ("addControll".equals(request.getParameter("command"))) {
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String patronymic = request.getParameter("patronymic");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			String login = request.getParameter("login");

			if (name.isEmpty() || surname.isEmpty() || password.isEmpty()) {
				response.sendRedirect(
						"MainController?command=GO_TO_REGISTRATION_PAGE&errorMessage=All required fields are not filled in!");
			} else if (password.equals(password2) != true) {
				response.sendRedirect(
						"MainController?command=GO_TO_REGISTRATION_PAGE&errorMessage=Passwords don't match!");

			} else {
				String tabelNumber = "" + IDGenerate.getNewID();
				boolean flag = serviceFactory.getUserService().isContain(login);
				if (flag == true) {
					//request.setAttribute("errorMessage", "The user with such login exists!");
					//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
					//dispatcher.forward(request, response);
					response.sendRedirect(
							"MainController?command=GO_TO_REGISTRATION_PAGE&errorMessage=A user with this username exists!");
				} else {
					User user = new User(login, 3, tabelNumber, password);
					Worker worker = new Worker(tabelNumber, login, name, surname, patronymic, null, null, null);
					serviceFactory.getUserService().setUser(user);
					serviceFactory.getWorkerService().setWorker(worker);
				}
				response.sendRedirect("MainController?command=GO_TO_CONTROL_PAGE");
			}
		}else if("editControll".equals(request.getParameter("command"))) {
			int num = Integer.parseInt(request.getParameter("id").trim());
			UserControl userControl = serviceFactory.getUserControlService().getUserControl(num);			
			int roleId = Integer.parseInt(request.getParameter("isRole"));
			if(roleId != 0) {
				userControl.setRoleID(roleId);				
			}			
		    userControl.setName(request.getParameter("name"));
		    userControl.setSurName(request.getParameter("surname"));
		    userControl.setPatronymic(request.getParameter("patronymic"));
		    userControl.setNumberOfPassport(request.getParameter("numpass"));
		    if(roleId == 4) {
		    	userControl.setPosition("Driver");
		    }else {
		    	userControl.setPosition(request.getParameter("position"));
		    }		    
		    userControl.setEMail(request.getParameter("email"));		    
			serviceFactory.getUserControlService().updateUserControl(userControl);
			response.sendRedirect("MainController?command=GO_TO_CONTROL_PAGE");				
		} else if ("delControll".equals(request.getParameter("command"))) {
			String num = request.getParameter("num");
			String login = serviceFactory.getWorkerService().getWorker(Integer.parseInt(num.trim())).getLogin();
			serviceFactory.getWorkerService().deleteWorker(Integer.parseInt(num.trim()));
			serviceFactory.getUserService().deleteUser(login);
			response.sendRedirect("MainController?command=GO_TO_CONTROL_PAGE");
		}
	}

}
