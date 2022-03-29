package by.myproject.main.entity;

import java.io.Serializable;
import java.util.Objects;

public class DispoEntry implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8846150909410542212L;
	private String numTrack;
	private String numTrailer;
	private int numOrder;
	private String route;
	private String dateLoad;
	private String dateCust;
	private String dateUnload;
	private String driver;
	private String cargo;
	private String typeOfTrailer;
	private String user;
	private String client;
	private String status;
	private String comment;
	
	public DispoEntry(String numTrack, String numTrailer, int numOrder, String route, String dateLoad, String dateCust,
			String dateUnload, String driver, String cargo, String typeOfTrailer, String user, String client,
			String status, String comment) {
		super();
		this.numTrack = numTrack;
		this.numTrailer = numTrailer;
		this.numOrder = numOrder;
		this.route = route;
		this.dateLoad = dateLoad;
		this.dateCust = dateCust;
		this.dateUnload = dateUnload;
		this.driver = driver;
		this.cargo = cargo;
		this.typeOfTrailer = typeOfTrailer;
		this.user = user;
		this.client = client;
		this.status = status;
		this.comment = comment;
	}

	public String getNumTrack() {
		return numTrack;
	}

	public void setNumTrack(String numTrack) {
		this.numTrack = numTrack;
	}

	public String getNumTrailer() {
		return numTrailer;
	}

	public void setNumTrailer(String numTrailer) {
		this.numTrailer = numTrailer;
	}

	public int getNumOrder() {
		return numOrder;
	}

	public void setNumOrder(int numOrder) {
		this.numOrder = numOrder;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
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

	public void setDateUnload(String dateUnload) {
		this.dateUnload = dateUnload;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getTypeOfTrailer() {
		return typeOfTrailer;
	}

	public void setTypeOfTrailer(String typeOfTrailer) {
		this.typeOfTrailer = typeOfTrailer;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cargo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DispoEntry other = (DispoEntry) obj;
		return Objects.equals(cargo, other.cargo);
	}

	@Override
	public String toString() {
		return "DispoEntry [numTrack=" + numTrack + ", numTrailer=" + numTrailer + ", numOrder=" + numOrder + ", route="
				+ route + ", dateLoad=" + dateLoad + ", dateCust=" + dateCust + ", dateUnload=" + dateUnload
				+ ", driver=" + driver + ", cargo=" + cargo + ", typeOfTrailer=" + typeOfTrailer + ", user=" + user
				+ ", client=" + client + ", status=" + status + "]";
	}
	
	
}
