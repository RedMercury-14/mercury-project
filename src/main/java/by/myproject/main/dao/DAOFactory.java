package by.myproject.main.dao;

import by.myproject.main.dao.impl.OrderDataBase;
import by.myproject.main.dao.impl.TruckDataBase;
import by.myproject.main.dao.impl.UserDataBase;
import by.myproject.main.dao.impl.WorkerDataBase;
import by.myproject.main.dao.impl.WorkerHasTruckDataBase;

public final class DAOFactory {

	private static volatile DAOFactory INSTANCE = null;
	
	private final OrderDataBase orderDataBase = new OrderDataBase();
	private final TruckDataBase truckDataBase = new TruckDataBase();
	private final UserDataBase userDataBase = new UserDataBase();
	private final WorkerDataBase workerDataBase = new WorkerDataBase();
	private final WorkerHasTruckDataBase workerHasTruckDataBase = new WorkerHasTruckDataBase();	
	
	public static DAOFactory getDAOFactory() {
		DAOFactory factory = INSTANCE;
		if (factory == null) {
			synchronized (DAOFactory.class) {
				factory = INSTANCE;
				if (factory == null) {
					INSTANCE = factory = new DAOFactory();
				}
			}
		}
		return factory;
	}
	public OrderDataBase getOrderDataBase() {
		return orderDataBase;
	}
	public TruckDataBase getTruckDataBase() {
		return truckDataBase;
	}
	public UserDataBase getUserDataBase() {
		return userDataBase;
	}
	public WorkerDataBase getWorkerDataBase() {
		return workerDataBase;
	}
	public WorkerHasTruckDataBase getWorkerHasTruckDataBase() {
		return workerHasTruckDataBase;
	}
	
	
}
