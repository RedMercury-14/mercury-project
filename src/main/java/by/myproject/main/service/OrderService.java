package by.myproject.main.service;

import java.util.List;

import by.myproject.main.dao.DAOException;
import by.myproject.main.dao.DAOFactory;
import by.myproject.main.dao.impl.OrderDataBase;
import by.myproject.main.entity.Order;

public class OrderService {
	private static final OrderDataBase orderDataBase = DAOFactory.getDAOFactory().getOrderDataBase();

	public Order getOrder(int num) throws ServiceException {		
		try {
			return orderDataBase.selectOne(num);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void setOrder(Order order) throws ServiceException {
		if(order == null) {
			throw new ServiceException("Object is null");
		}
		
		try {
			orderDataBase.insert(order);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void updateOrder(Order order) throws ServiceException {
		if(order == null) {
			throw new ServiceException("Object is null");
		}
		try {
			orderDataBase.update(order);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void deleteOrder(int num) throws ServiceException {
		try {
			orderDataBase.delete(num);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public List<Order> getOrderList() throws ServiceException {
		try {
			return orderDataBase.select();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}
}
