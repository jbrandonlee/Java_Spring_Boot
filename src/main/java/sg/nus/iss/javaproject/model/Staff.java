package sg.nus.iss.javaproject.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Staff implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int employeeId;
	private String employeeName;
	private String employeeDesignation;
	private int employeeDeptId;
	private String employeeEmail;
	private LocalDate employeeJoinDate;
	private int managedBy;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
	@OneToMany(mappedBy="staff")
	private List<LeaveEntitlement> leaveEntitlement;
	
	@OneToMany(mappedBy="staff")
	private List<LeaveApplication> leaveApplication;
	
	@OneToMany(mappedBy="staff")
	private List<OverTimeWork> overTimeWork;
	
	public Staff() {}
		
	public Staff(String employeeName, String employeeDesignation) {
		super();
		this.employeeName = employeeName;
		this.employeeDesignation = employeeDesignation;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public int getEmployeeDeptId() {
		return employeeDeptId;
	}

	public void setEmployeeDeptId(int employeeDeptId) {
		this.employeeDeptId = employeeDeptId;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public LocalDate getEmployeeJoinDate() {
		return employeeJoinDate;
	}

	public void setEmployeeJoinDate(LocalDate employeeJoinDate) {
		this.employeeJoinDate = employeeJoinDate;
	}

	public int getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(int managedBy) {
		this.managedBy = managedBy;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}


	public List<LeaveEntitlement> getLeaveEntitlement() {
		return leaveEntitlement;
	}

	public void setLeaveEntitlement(List<LeaveEntitlement> leaveEntitlement) {
		this.leaveEntitlement = leaveEntitlement;
	}

	public List<LeaveApplication> getLeaveApplication() {
		return leaveApplication;
	}

	public void setLeaveApplication(List<LeaveApplication> leaveApplication) {
		this.leaveApplication = leaveApplication;
	}

	public List<OverTimeWork> getOverTimeWork() {
		return overTimeWork;
	}

	public void setOverTimeWork(List<OverTimeWork> overTimeWork) {
		this.overTimeWork = overTimeWork;
	}

	@Override
	public String toString() {
		return "Staff [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeDesignation="
				+ employeeDesignation + ", employeeDeptId=" + employeeDeptId + ", employeeEmail=" + employeeEmail
				+ ", employeeJoinDate=" + employeeJoinDate + ", managedBy=" + managedBy + ", password=" + password
				+ ", userRole=" + userRole +  ", leaveEntitlement=" + leaveEntitlement
				+ ", leaveApplication=" + leaveApplication + ", overTimeWork=" + overTimeWork + "]";
	}
}
