package by.myproject.main.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserControl implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 938219945102658221L;
	private String login;
	private int roleID;
	private String role;
    private String id;    
    private String name;
    private String surName;
    private String patronymic;
    private String numberOfPassport;
    private String position;
    private String eMail;
    private String password;
    
	public UserControl() {
		super();
	}
	public UserControl(String login, int roleID, String role, String id,
			String name, String surName, String patronymic, String numberOfPassport, String position, String eMail, String password) {
		super();
		this.login = login;
		this.roleID = roleID;
		this.role = role;
		this.id = id;
		this.name = name;
		this.surName = surName;
		this.patronymic = patronymic;
		this.numberOfPassport = numberOfPassport;
		this.position = position;
		this.eMail = eMail;
		this.password = password;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getEMail() {
		return eMail;
	}
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserControl other = (UserControl) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "UserControl [login=" + login + ", roleID=" + roleID + ", role=" + role + ", id=" + id + ", name=" + name
				+ ", surName=" + surName + ", patronymic=" + patronymic + ", numberOfPassport=" + numberOfPassport
				+ ", position=" + position + ", eMail=" + eMail + "]";
	}

	
    
}
