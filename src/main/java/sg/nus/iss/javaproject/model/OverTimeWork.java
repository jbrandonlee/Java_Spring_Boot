package sg.nus.iss.javaproject.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OverTimeWork {
	@Id
	private LocalDate overtimeDate;
	private LocalDate overtimeStartTime;
	private LocalDate overtimeEndTime;
	private String compensationEntitlement;	
	
	@ManyToOne
	private Staff staff;
	
	public OverTimeWork(LocalDate overtimeDate, LocalDate overtimeStartTime, LocalDate overtimeEndTime,
			String compensationEntitlement) {
		super();
		this.overtimeDate = overtimeDate;
		this.overtimeStartTime = overtimeStartTime;
		this.overtimeEndTime = overtimeEndTime;
		this.compensationEntitlement = compensationEntitlement;
	}
	
	public LocalDate getOvertimeDate() {
		return overtimeDate;
	}

	public void setOvertimeDate(LocalDate overtimeDate) {
		this.overtimeDate = overtimeDate;
	}

	public LocalDate getOvertimeStartTime() {
		return overtimeStartTime;
	}

	public void setOvertimeStartTime(LocalDate overtimeStartTime) {
		this.overtimeStartTime = overtimeStartTime;
	}

	public LocalDate getOvertimeEndTime() {
		return overtimeEndTime;
	}

	public void setOvertimeEndTime(LocalDate overtimeEndTime) {
		this.overtimeEndTime = overtimeEndTime;
	}

	public String getCompensationEntitlement() {
		return compensationEntitlement;
	}

	public void setCompensationEntitlement(String compensationEntitlement) {
		this.compensationEntitlement = compensationEntitlement;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "OverTimeWork [overtimeDate=" + overtimeDate + ", overtimeStartTime=" + overtimeStartTime
				+ ", overtimeEndTime=" + overtimeEndTime + ", compensationEntitlement=" + compensationEntitlement
				+ ", staff=" + staff + "]";
	}
}
