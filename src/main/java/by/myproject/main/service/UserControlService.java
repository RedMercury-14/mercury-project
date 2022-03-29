package by.myproject.main.service;

import java.util.ArrayList;
import java.util.List;

import by.myproject.main.dao.DAOException;
import by.myproject.main.dao.DAOFactory;
import by.myproject.main.dao.UserDataBase;
import by.myproject.main.dao.impl.WorkerDataBase;
import by.myproject.main.entity.Role;
import by.myproject.main.entity.User;
import by.myproject.main.entity.UserControl;
import by.myproject.main.entity.Worker;

public class UserControlService {
	private static final WorkerDataBase workerDataBase = DAOFactory.getDAOFactory().getWorkerDataBase();
	private static final UserDataBase<User> userDataBase = DAOFactory.getDAOFactory().getUserDataBase();
	
	public UserControl getUserControl(int num) throws ServiceException {		
		try {
			Worker worker = workerDataBase.selectOne(num);
			User user = userDataBase.selectOne(worker.getLogin());
			Role role = userDataBase.selectOneRole(user.getRoleID());
			UserControl userControl = new UserControl();
			userControl.setId(user.getTabelNumber());
			userControl.setLogin(user.getLogin());
			userControl.setName(worker.getName());
			userControl.setNumberOfPassport(worker.getNumberOfPassport());
			userControl.setPatronymic(worker.getPatronymic());
			userControl.setPosition(worker.getPosition());
			userControl.setRole(role.getRole());
			userControl.setRoleID(role.getId());
			userControl.setSurName(worker.getSurName());
			userControl.setEMail(worker.geteMail());
			userControl.setPassword(user.getPassword());
			return userControl;			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	public List<UserControl> getUserControlList() throws ServiceException {
		try {
			List<User> userArray =  userDataBase.select();
			List<UserControl> userControlArray = new ArrayList<UserControl>();
			for (User user : userArray) {
				int num = Integer.parseInt(user.getTabelNumber());
				Worker worker = workerDataBase.selectOne(num);
				Role role = userDataBase.selectOneRole(user.getRoleID());
				UserControl userControl = new UserControl();
				userControl.setId(user.getTabelNumber());
				userControl.setLogin(user.getLogin());
				userControl.setName(worker.getName());
				userControl.setNumberOfPassport(worker.getNumberOfPassport());
				userControl.setPatronymic(worker.getPatronymic());
				userControl.setPosition(worker.getPosition());
				userControl.setRole(role.getRole());
				userControl.setRoleID(role.getId()); 
				userControl.setSurName(worker.getSurName());
				userControl.setEMail(worker.geteMail());
				userControl.setPassword(user.getPassword());
				userControlArray.add(userControl);
			}
			return userControlArray;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}	
	public void updateUserControl(UserControl userControl) throws ServiceException {
		if(userControl == null) {
			throw new ServiceException("Object is null");
		}
		try {
			String login = userControl.getLogin();
			int roleID = userControl.getRoleID();
			String id = userControl.getId();    
		    String name = userControl.getName();
		    String surName = userControl.getSurName();
		    String patronymic = userControl.getPatronymic();
		    String numberOfPassport = userControl.getNumberOfPassport();
		    String position = userControl.getPosition();
		    String eMail = userControl.getEMail();
		    String passwword = userControl.getPassword();
		    User user = new User(login, roleID, id, passwword);
		    Worker worker = new Worker(id, login, name, surName, patronymic, numberOfPassport, position, eMail);
		    userDataBase.update(user);
		    workerDataBase.update(worker);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	/*
	public void deleteOrder(int num) throws ServiceException {
		try {
			orderDataBase.delete(num);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	

	}*/

}
