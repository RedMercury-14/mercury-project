package by.myproject.main.entity;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable{


	 /**
	 * 
	 */
	private static final long serialVersionUID = -4107328218389527540L;
	private int id;
	 private String role;
	public Role(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public int hashCode() {
		return Objects.hash(role);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(role, other.role);
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + "]";
	}
	 
	 

}
