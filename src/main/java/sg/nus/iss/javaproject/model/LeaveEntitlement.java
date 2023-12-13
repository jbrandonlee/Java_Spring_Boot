package sg.nus.iss.javaproject.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LeaveEntitlement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int leaveEntitlementId;
	
	private int leaveDays;
	private LocalDate leaveStart;
	
	@ManyToOne
	private Staff staff;

	public LeaveEntitlement(int leaveDays, LocalDate leaveStart) {
		super();
		this.leaveDays = leaveDays;
		this.leaveStart = leaveStart;
	}

	public int getLeaveEntitlementId() {
		return leaveEntitlementId;
	}

	public void setLeaveEntitlementId(int leaveEntitlementId) {
		this.leaveEntitlementId = leaveEntitlementId;
	}

	public int getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(int leaveDays) {
		this.leaveDays = leaveDays;
	}

	public LocalDate getLeaveStart() {
		return leaveStart;
	}

	public void setLeaveStart(LocalDate leaveStart) {
		this.leaveStart = leaveStart;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "LeaveEntitlement [leaveEntitlementId=" + leaveEntitlementId + ", leaveDays=" + leaveDays
				+ ", leaveStart=" + leaveStart + ", staff=" + staff + "]";
	}
}
