package sg.nus.iss.javaproject.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Account implements Serializable{
	@Id
	@NotBlank(message = "error.user.name.empty")
	private String username;
	@NotBlank(message = "error.user.password.empty")
	private String password;
	
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
	
	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", staff=" + staff +  "]";
	} 
}
