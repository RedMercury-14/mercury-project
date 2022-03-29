package by.myproject.main.entity;


import java.io.Serializable;
import java.util.Objects;



public class Worker implements Serializable {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = -1482144787398774950L;
	private String id;
    private String login;
    private String name;
    private String surName;
    private String patronymic;
    private String numberOfPassport;
    private String position;
    private String eMail;
    


	public Worker(String id, String login, String name, String surName, String patronymic, String numberOfPassport,
			String position, String eMail) {
		super();
		this.id = id;
		this.login = login;
		this.name = name;
		this.surName = surName;
		this.patronymic = patronymic;
		this.numberOfPassport = numberOfPassport;
		this.position = position;
		this.eMail = eMail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public  void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getPatronymic() {
		return patronymic;
	}
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	public String getNumberOfPassport() {
		return numberOfPassport;
	}
	public void setNumberOfPassport(String numberOfPassport) {
		this.numberOfPassport = numberOfPassport;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
		public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
		@Override
		public int hashCode() {
			return Objects.hash(id, login);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Worker other = (Worker) obj;
			return Objects.equals(id, other.id) && Objects.equals(login, other.login);
		}
		@Override
		public String toString() {
			return "Worker [id=" + id + ", login=" + login + ", name=" + name + ", surName=" + surName + ", patronymic="
					+ patronymic + ", numberOfPassport=" + numberOfPassport + ", position=" + position + ", eMail="
					+ eMail + "]";
		}
 
}



