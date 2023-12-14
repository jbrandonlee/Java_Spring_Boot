package sg.edu.nus.iss.javaca.model;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {
	
	//attributes
	@Id
	@Column(name = "EmployeeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Designation")
	private String designation;
	
	@Column(name = "EmailAddress")
	private String emailAddress;
	
	@Column(name = "JoinDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate joinDate;
	
	@Column(name = "ManagerId")
	private String managerId;
	
	@OneToOne(mappedBy = "employee")
	private User user;
	
	@OneToMany(mappedBy = "employee")
	private List<Leave> leaves;
	
	@ManyToMany(targetEntity = Role.class, cascade = { CascadeType.ALL, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "EmpRole", 
	joinColumns = @JoinColumn(name = "EmployeeId") , 
	inverseJoinColumns = @JoinColumn(name = "RoleId") )
	private List<Role> roles;
	
	public Employee() {}
	
	public Employee(int employeeId, String name, String designation, String emailAddress, LocalDate joinDate, String managerId) {
		this.employeeId = employeeId;
		this.name = name;
		this.designation = designation;
		this.emailAddress = emailAddress;
		this.joinDate = joinDate;
		this.managerId = managerId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return emailAddress;
	}

	public void setEmail(String email) {
		this.emailAddress = email;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

}
