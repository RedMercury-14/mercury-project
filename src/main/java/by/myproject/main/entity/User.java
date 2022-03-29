package by.myproject.main.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
	private static final long serialVersionUID = 6831245179946846195L;
	private String login;
	private int roleID;
	private String tabelNumber;
	private String password;
	
	public User(String login, int roleID, String tabelNumber, String password) {
		super();
		this.login = login;
		this.roleID = roleID;
		this.tabelNumber = tabelNumber;
		this.password = password;
	}
	public User(String login, int roleID, String tabelNumber) {
		super();
		this.login = login;
		this.roleID = roleID;
		this.tabelNumber = tabelNumber;
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
	public String getTabelNumber() {
		return tabelNumber;
	}
	public void setTabelNumber(String tabelNumber) {
		this.tabelNumber = tabelNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		return Objects.hash(login);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(login, other.login);
	}
	@Override
	public String toString() {
		return "User [login=" + login + ", roleID=" + roleID + ", tabelNumber=" + tabelNumber + ", Password=" + password
				+ "]";
	}

}
