package by.myproject.main.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.myproject.main.controller.Command;
import by.myproject.main.controller.command.DispositionCommand;
import by.myproject.main.entity.DispoEntry;
import by.myproject.main.entity.Order;
import by.myproject.main.entity.Truck;
import by.myproject.main.entity.WorkerHasTruck;
import by.myproject.main.service.ServiceException;
import by.myproject.main.service.ServiceFactory;

public class GoToDispositionPage implements Command{
	private ServiceFactory serviceFactory = ServiceFactory.getServiceFactory();
	private static final Logger log = Logger.getLogger(GoToDispositionPage.class);

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		List<Order> order = serviceFactory.getOrderService().getOrderList();
		List<DispoEntry> entryList = new ArrayList<DispoEntry>();	
		List<WorkerHasTruck> wayList = new ArrayList<WorkerHasTruck>();		
		for (Order entry : order) {
			String numTrack;
			if(entry.getCarNum() == null) {
				continue;
			}else {
				numTrack = entry.getCarNum().trim();
			}			
			Truck truck = serviceFactory.getTruckService().getTruck(numTrack);
			String numTrailer = truck.getNumTr();
			int numOrder = entry.getNumOrd();
			String route = entry.getRoute();
			String dateLoad = entry.getDateLoad();
			String dateCust = entry.getDateCust();
			String dateUnload = entry.getDateUnload();
			String driver = null;
			wayList = serviceFactory.getWorkerHasTruckService().searchWaybills(numTrack);
			for (WorkerHasTruck workerEntry : wayList) {
				if(workerEntry.getDateOfFinish() == null) {
					driver = workerEntry.getWorkersPosition();						
				}else {
					driver = "driver not found";				
				}
			}
			String cargo = entry.getCargo();
			String typeOfTrailer = truck.getTypeTr();
			String user = entry.getUser();
			String client = entry.getClient();
			String status = entry.getStatus();
			String comment = entry.getOrdCol();
			if(status == null) {				
				continue;
			}else {
				DispoEntry dispoEntry = new DispoEntry(numTrack, numTrailer, numOrder, route, dateLoad, dateCust, dateUnload, driver, cargo, typeOfTrailer, user, client, status, comment);
				entryList.add(dispoEntry);
			}
			
		}
		request.setAttribute("Entry", entryList);	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/disposition.jsp");
		dispatcher.forward(request, response);
		
	}

}
