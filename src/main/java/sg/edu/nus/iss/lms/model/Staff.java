package sg.edu.nus.iss.lms.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Staff implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int employeeId;
	private String employeeName;
	private String employeeDesignation;
	private String employeeEmail;
	private LocalDate employeeJoinDate;
	private int managedBy;
			
	@OneToOne
	private Account account;
	
	@OneToMany(mappedBy="staff")
	private List<LeaveApplication> leaveApplication;

	@OneToMany(mappedBy="staff")
	private List<OverTimeWork> overTimeWork;
	
	@ManyToMany
	private List<StaffRole> staffRole;
	
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Staff [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeDesignation="
				+ employeeDesignation + ", employeeEmail=" + employeeEmail
				+ ", employeeJoinDate=" + employeeJoinDate + ", managedBy=" + managedBy + ", account=" + account
				+", leaveApplication=" + leaveApplication
				+ ", overTimeWork=" + overTimeWork + "]";
	}

	
}
