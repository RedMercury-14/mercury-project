package by.myproject.main.entity;

import java.io.Serializable;
import java.util.Objects;


public class Order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1558456087975957598L;
	private int numOrd;
	private String route;
	private String ordCol;
	private String dateLoad;
	private String dateCust;
	private String dateUnload;
	private String client;
	private String prise;
	private String cargo;
	private String carNum;
	private String user;
	private String admin;
	private String status;	
	
	public Order() {};
	
	public Order(int numOrd, String route, String ordCol, String dateLoad, String dateCust, String dateUnload, String client,
			String prise, String cargo, String user) {
		super();
		this.numOrd = numOrd;
		this.route = route;
		this.ordCol = ordCol;		
		this.dateLoad = dateLoad;
		this.dateCust = dateCust;
		this.dateUnload = dateUnload;
		this.client = client;
		this.prise = prise;
		this.cargo = cargo;
		this.user = user;
	}
	
	public Order(int numOrd, String route, String ordCol, String dateLoad, String dateCust, String dateUnload, String client,
			String prise, String cargo, String user, String carNum) {
		super();
		this.numOrd = numOrd;
		this.route = route;
		this.ordCol = ordCol;		
		this.dateLoad = dateLoad;
		this.dateCust = dateCust;
		this.dateUnload = dateUnload;
		this.client = client;
		this.prise = prise;
		this.cargo = cargo;
		this.user = user;
		this.carNum = carNum;
	}
	
	public Order(String route, String ordCol, String dateLoad, String dateCust, String dateUnload, String client,
			String prise, String cargo, String user) {
		super();		
		this.route = route;
		this.ordCol = ordCol;		
		this.dateLoad = dateLoad;	
		this.dateCust = dateCust;		
		this.dateUnload = dateUnload;
		this.client = client;
		this.prise = prise;
		this.cargo = cargo;
		this.user = user;
	}

	public Order(int numOrd, String route, String ordCol, String dateLoad, String dateCust, String dateUnload,
			String client, String prise, String cargo, String carNum, String user, String admin, String status) {
		super();		
		this.numOrd = numOrd;
		this.route = route;
		this.ordCol = ordCol;
		this.dateLoad = dateLoad;
		this.dateCust = dateCust;
		this.dateUnload = dateUnload;
		this.client = client;
		this.prise = prise;
		this.cargo = cargo;
		this.carNum = carNum;
		this.user = user;
		this.admin = admin;
		this.status = status;
	}
	public int getNumOrd() {
		return numOrd;
	}
	public void setNumOrd(int numOrd) {
		this.numOrd = numOrd;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getOrdCol() {
		return ordCol;
	}
	public void setOrdCol(String ordCol) {
		this.ordCol = ordCol;
	}
	public String getDateLoad() {
		return dateLoad;
	}
	public void setDateLoad(String dateLoad) {		
		this.dateLoad = dateLoad;
	}
	public String getDateCust() {
		return dateCust;
	}
	public void setDateCust(String dateCust) {
		this.dateCust = dateCust;
	}
	public String getDateUnload() {
		return dateUnload;
	}
	public void setDateUnload(String dateUnload)
	{
		this.dateUnload = dateUnload;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getPrise() {
		return prise;
	}
	public void setPrise(String prise) {
		this.prise = prise;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numOrd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return numOrd == other.numOrd;
	}

	@Override
	public String toString() {
		return "Order [numOrd=" + numOrd + ", route=" + route + ", ordCol=" + ordCol + ", dateLoad=" + dateLoad
				+ ", dateCust=" + dateCust + ", dateUnload=" + dateUnload + ", client=" + client + ", prise=" + prise
				+ ", cargo=" + cargo + ", carNum=" + carNum + ", user=" + user + ", admin=" + admin + ", status="
				+ status + "]";
	}

}
