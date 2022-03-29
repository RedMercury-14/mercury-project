package by.myproject.main.entity;

import java.io.Serializable;
import java.util.Objects;

public class WorkerHasTruck implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1705923709744230891L;
	private int id;
	private int idWorkers;
	private String trucksNumber;
	private String workersPosition;
	private String dateOfStart;
	private String dateOfFinish;
	
	
	
	
	public WorkerHasTruck(int id, int idWorkers, String trucksNumber, String workersPosition, String dateOfStart,
			String dateOfFinish) {
		super();
		this.id = id;
		this.idWorkers = idWorkers;
		this.trucksNumber = trucksNumber;
		this.workersPosition = workersPosition;
		this.dateOfStart = dateOfStart;
		this.dateOfFinish = dateOfFinish;
		}	
	

	public WorkerHasTruck(int idWorkers, String trucksNumber, String workersPosition, String dateOfStart,
			String dateOfFinish) {
		super();
		this.idWorkers = idWorkers;
		this.trucksNumber = trucksNumber;
		this.workersPosition = workersPosition;
		this.dateOfStart = dateOfStart;
		this.dateOfFinish = dateOfFinish;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdWorkers() {
		return idWorkers;
	}
	public void setIdWorkers(int idWorkers) {
		this.idWorkers = idWorkers;
	}
	public String getTrucksNumber() {
		return trucksNumber;
	}
	public void setTrucksNumber(String trucksNumber) {
		this.trucksNumber = trucksNumber;
	}
	public String getWorkersPosition() {
		return workersPosition;
	}
	public void setWorkersPosition(String workersPosition) {
		this.workersPosition = workersPosition;
	}
	public String getDateOfStart() {
		return dateOfStart;
	}
	public void setDateOfStart(String dateOfStart) {
		this.dateOfStart = dateOfStart;
	}
	public String getDateOfFinish() {
		return dateOfFinish;
	}
	public void setDateOfFinish(String dateOfFinish) {
		this.dateOfFinish = dateOfFinish;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, idWorkers, trucksNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkerHasTruck other = (WorkerHasTruck) obj;
		return id == other.id && idWorkers == other.idWorkers && Objects.equals(trucksNumber, other.trucksNumber);
	}


	@Override
	public String toString() {
		return "WorkerHasTruck [id=" + id + ", idWorkers=" + idWorkers + ", trucksNumber=" + trucksNumber
				+ ", workersPosition=" + workersPosition + ", dateOfStart=" + dateOfStart + ", dateOfFinish="
				+ dateOfFinish + "]";
	}
	
	
}
