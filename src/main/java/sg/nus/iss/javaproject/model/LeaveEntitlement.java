package sg.nus.iss.javaproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LeaveEntitlement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private LeaveType leaveType;
	
	private Integer entitlement;
	
	private Integer leaveBalance;
	
	@ManyToOne
	private Staff staff;
	
	// TestData Constructor
	public LeaveEntitlement(Staff staff, LeaveType leaveType, Integer entitlement) {
		this.staff = staff;
		this.leaveType = leaveType;
		this.entitlement = entitlement;
		this.leaveBalance = entitlement;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public Integer getEntitlement() {
		return entitlement;
	}

	public void setEntitlement(Integer entitlement) {
		this.entitlement = entitlement;
	}

	public Integer getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(Integer leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
}
