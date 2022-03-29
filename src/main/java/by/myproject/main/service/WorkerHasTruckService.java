package by.myproject.main.service;

import java.util.List;

import by.myproject.main.dao.DAOException;
import by.myproject.main.dao.DAOFactory;
import by.myproject.main.dao.impl.WorkerHasTruckDataBase;
import by.myproject.main.entity.WorkerHasTruck;
import by.myproject.main.util.Validator;

public class WorkerHasTruckService {
private static final WorkerHasTruckDataBase workerHasTruckDataBase = DAOFactory.getDAOFactory().getWorkerHasTruckDataBase();
	
	public WorkerHasTruck getWorkerHasTruck(int num) throws ServiceException {
		try {
			return workerHasTruckDataBase.selectOne(num);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void setWorkerHasTruck(WorkerHasTruck workerHasTruck) throws ServiceException {
		if(workerHasTruck == null) {
			throw new ServiceException("Object is null");
		}
		try {
			workerHasTruckDataBase.insert(workerHasTruck);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateWorkerHasTruck(WorkerHasTruck workerHasTruck) throws ServiceException {
		if(workerHasTruck == null) {
			throw new ServiceException("Object is null");
		}
		try {
			workerHasTruckDataBase.update(workerHasTruck);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void deleteWorkerHasTruck(int num) throws ServiceException {
		try {
			workerHasTruckDataBase.delete(num);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public List<WorkerHasTruck> getWorkerHasTruck() throws ServiceException {
		try {
			return workerHasTruckDataBase.select();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}
	
	public List<WorkerHasTruck> searchWaybills(String trucksNumber) throws ServiceException {
		if(Validator.isEmptyOfNull(trucksNumber)) {
			throw new ServiceException("Command is null");
		}
		try {
			return workerHasTruckDataBase.searchWaybills(trucksNumber);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

}
