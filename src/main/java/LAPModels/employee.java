package LAPModels;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int employeeId;
	
	@Column
	private String name;
	 
	@Column
	private String managerId;
	
	@Column
	private String designation;
	
	@Column
	private String email;
	@Column
	private LocalDate employeeJoinDate;
	
	@OneToMany(mappedBy = "employee")
	private List<leaveApplication> leaveAppls;
	
	@OneToMany(mappedBy = "employee")
	private List<overtimeWork> ovtWorks;
	
	@OneToOne
	private leaveEntitlement leaveEtt;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getEmployeeJoinDate() {
		return employeeJoinDate;
	}
	public void setEmployeeJoinDate(LocalDate employeeJoinDate) {
		this.employeeJoinDate = employeeJoinDate;
	}
	
}
