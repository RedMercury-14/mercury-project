package by.myproject.main.entity;

import java.io.Serializable;
import java.util.Objects;

public class Truck implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2782479572454067510L;
	private String numCar;
	private String numTr;
	private String carModel;
	private String trModel;
	private String typeTr;
	private String dateCar;
	private String dateTr;

	public Truck() {
	}

	public Truck(String numCar, String numTr, String carModel, String trModel, String typeTr, String dateCar,
			String dateTr) {
		super();
		this.numCar = numCar;
		this.numTr = numTr;
		this.carModel = carModel;
		this.trModel = trModel;
		this.typeTr = typeTr;
		this.dateCar = dateCar;
		this.dateTr = dateTr;
	}

	public String getNumCar() {
		return numCar;
	}

	public void setNumCar(String numCar) {
		this.numCar = numCar;
	}

	public String getNumTr() {
		return numTr;
	}

	public void setNumTr(String numTr) {
		this.numTr = numTr;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getTrModel() {
		return trModel;
	}

	public void setTrModel(String trModel) {
		this.trModel = trModel;
	}

	public String getTypeTr() {
		return typeTr;
	}

	public void setTypeTr(String typeTr) {
		this.typeTr = typeTr;
	}

	public String getDateCar() {
		return dateCar;
	}

	public void setDateCar(String dateCar) {
		this.dateCar = dateCar;
	}

	public String getDateTr() {
		return dateTr;
	}

	public void setDateTr(String dateTr) {
		this.dateTr = dateTr;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numCar);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Truck other = (Truck) obj;
		return numCar == other.numCar;
	}

	@Override
	public String toString() {
		return "Truck [numCar=" + numCar + ", numTr=" + numTr + ", carModel=" + carModel + ", trModel=" + trModel
				+ ", typeTr=" + typeTr + ", dateCar=" + dateCar + ", dateTr=" + dateTr + "]";
	}

}
