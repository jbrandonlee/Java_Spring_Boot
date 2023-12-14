package sg.edu.nus.iss.javaca.model;


import jakarta.persistence.*;

@Entity
@Table(name = "App_User")
public class User {
	
	//attributes
	
	@Id
	@Column(name = "UserId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(name = "Username")
	private String userName;
	
	@Column(name = "Password")
	private String password;
	
	@OneToOne
	@JoinColumn(name = "EmployeeId")
	private Employee employee;
	
	public User() {}
	
	public User(int userId, String userName, String password) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
