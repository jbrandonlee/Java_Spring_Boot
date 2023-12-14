package sg.nus.iss.javaproject.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Account implements Serializable{
	@Id
	private String username;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
	@OneToOne(mappedBy="account")
	private Staff staff;
	
	public Account() {}
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", staff=" + staff +",userRole="+userRole+ "]";
	} 
}
