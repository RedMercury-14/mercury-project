package by.myproject.main.service;

import java.util.List;

import by.myproject.main.dao.DAOException;
import by.myproject.main.dao.DAOFactory;
import by.myproject.main.dao.config.ConnectionPoolException;
import by.myproject.main.dao.impl.WorkerDataBase;
import by.myproject.main.entity.Worker;
import by.myproject.main.util.Validator;

public class WorkerService {
private static final WorkerDataBase workerDataBase = DAOFactory.getDAOFactory().getWorkerDataBase();
	
	public Worker getWorker(int num) throws ServiceException {
		try {
			return workerDataBase.selectOne(num);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void setWorker(Worker worker) throws ServiceException {
		if(worker == null) {
			throw new ServiceException("Object is null");
		}
		try {
			workerDataBase.insert(worker);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateWorker(Worker worker) throws ServiceException {
		if(worker == null) {
			throw new ServiceException("Object is null");
		}
		try {
			workerDataBase.update(worker);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void deleteWorker(int num) throws ServiceException {
		try {
			workerDataBase.delete(num);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public List<Worker> getWorkerList() throws ServiceException {
		try {
			return workerDataBase.select();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public boolean isContain(String login) throws ServiceException {
		if(Validator.isEmptyOfNull(login)) {
			throw new ServiceException("Command is null");
		}
		try {
			return workerDataBase.isContain(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	public boolean isUser(String login, String role) throws ServiceException {
		if(Validator.isEmptyOfNull(login) || Validator.isEmptyOfNull(role)) {
			throw new ServiceException("Command is null");
		}
		try {
			return workerDataBase.isUser(login, role);
		} catch (DAOException | ConnectionPoolException e) {
			throw new ServiceException(e);
		}
	}
	public String getRole(String login) throws ServiceException {
		if(Validator.isEmptyOfNull(login)) {
			throw new ServiceException("Command is null");
		}
		try {
			return workerDataBase.getRole(login);
		} catch (DAOException | ConnectionPoolException e) {
			throw new ServiceException(e);
		}
	}
	

}
