package by.myproject.main.service;

import java.util.List;

import by.myproject.main.dao.DAOException;
import by.myproject.main.dao.DAOFactory;
import by.myproject.main.dao.TruckDataBase;
import by.myproject.main.entity.Truck;
import by.myproject.main.service.valid.NotDataFieldException;
import by.myproject.main.service.valid.NotDateFormatException;
import by.myproject.main.service.valid.TruckValidation;
import by.myproject.main.util.Validator;

public class TruckService {
	private static final TruckDataBase<Truck> truckDataBase = DAOFactory.getDAOFactory().getTruckDataBase();
	
	public Truck getTruck(String num) throws ServiceException {
		if(Validator.isEmptyOfNull(num)) {
			throw new ServiceException("Command is null");
		}
		try {
			return truckDataBase.selectOne(num);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void setTruck(Truck truck) throws ServiceException, NotDateFormatException, NotDataFieldException {
		new TruckValidation(truck);		
		try {
			truckDataBase.insert(truck);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateTruck(Truck truck) throws ServiceException, NotDateFormatException, NotDataFieldException {
		new TruckValidation(truck);	
		try {
			truckDataBase.update(truck);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void deleteTruck(String num) throws ServiceException {
		try {
			truckDataBase.delete(num);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public List<Truck> getTruckList() throws ServiceException {
		try {
			return truckDataBase.select();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

}
