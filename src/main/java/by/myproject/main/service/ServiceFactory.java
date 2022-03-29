package by.myproject.main.service;

import by.myproject.main.entity.UserControl;

public class ServiceFactory {
	private static volatile ServiceFactory INSTANCE = null;
	private final OrderService orderService = new OrderService(); 
	private final TruckService truckService = new TruckService();
	private final WorkerHasTruckService workerHasTruckService = new WorkerHasTruckService();
	private final WorkerService workerService = new WorkerService();
	private final UserService userService = new UserService();
	private final UserControlService userControlService = new UserControlService();
	
	
	public static ServiceFactory getServiceFactory() {
		ServiceFactory factory = INSTANCE;
		if (factory == null) {
			synchronized (ServiceFactory.class) {
				factory = INSTANCE;
				if (factory == null) {
					INSTANCE = factory = new ServiceFactory();
				}
			}
		}
		return factory;
	}
	public OrderService getOrderService() {
		return orderService;
	}


	public TruckService getTruckService() {
		return truckService;
	}


	public WorkerHasTruckService getWorkerHasTruckService() {
		return workerHasTruckService;
	}


	public WorkerService getWorkerService() {
		return workerService;
	}


	public UserService getUserService() {
		return userService;
	}
	public UserControlService getUserControlService() {
		return userControlService;
	}
	
	
	

	
}
