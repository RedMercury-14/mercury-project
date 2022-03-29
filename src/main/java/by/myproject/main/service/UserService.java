package by.myproject.main.service;

import java.util.List;

import org.apache.log4j.Logger;

import by.myproject.main.dao.DAOException;
import by.myproject.main.dao.DAOFactory;
import by.myproject.main.dao.UserDataBase;
import by.myproject.main.entity.Role;
import by.myproject.main.entity.User;
import by.myproject.main.util.Validator;

public class UserService {

private static final UserDataBase<User> userDataBase = DAOFactory.getDAOFactory().getUserDataBase();
private static final Logger log = Logger.getLogger(UserService.class);
//валидатор: проверка длинны логина, проверка наличия роли
	
	public User getUser(String num) throws ServiceException {
		if(Validator.isEmptyOfNull(num)) {
			throw new ServiceException("Command is null");
		}
		try {
			return userDataBase.selectOne(num);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void setUser(User user) throws ServiceException {
		if(user == null) {
			throw new ServiceException("Object is null");
		}
		try {
			userDataBase.insert(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateUser(User user) throws ServiceException {
		if(user == null) {
			throw new ServiceException("Object is null");
		}
		try {
			userDataBase.update(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void deleteUser(String num) throws ServiceException {
		if(Validator.isEmptyOfNull(num)) {
			throw new ServiceException("Command is null");
		}
		try {
			userDataBase.delete(num);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public List<User> getUserList() throws ServiceException {
		try {
			return userDataBase.select();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}
	public boolean isUser(String login, String password) throws ServiceException {
		if(Validator.isEmptyOfNull(login) || Validator.isEmptyOfNull(password)) {
			throw new ServiceException("Command is null");
		}
		try {
			return userDataBase.isUser(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	public boolean isContain(String login) throws ServiceException {
		if(Validator.isEmptyOfNull(login)) {
			throw new ServiceException("Command is null");
		}
		try {
			return userDataBase.isContain(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	public List<Role> getRoleList() throws ServiceException {
		try {
			return userDataBase.selectRole();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	public Role getRole(int num) throws ServiceException {	
		try {
			return userDataBase.selectOneRole(num);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
}
