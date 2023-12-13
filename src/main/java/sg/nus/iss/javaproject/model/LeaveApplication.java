package sg.nus.iss.javaproject.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LeaveApplication {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int leaveId;
	@Enumerated(EnumType.STRING)
	private LeaveType leaveType;
	private LocalDate leaveStartDate;
	private LocalDate leaveEndDate;
	private String leaveDetails;
	private String leavePointOfContact;
	private String leavePhoneNumber;
	@Enumerated(EnumType.STRING)
	private ApprovalStatus leaveApprovalStatus;
	
	@ManyToOne
	private Staff staff;
	
	public LeaveApplication(LeaveType leaveType, LocalDate leaveStartDate, LocalDate leaveEndDate,
			ApprovalStatus leaveApprovalStatus) {
		super();
		this.leaveType = leaveType;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.leaveApprovalStatus = leaveApprovalStatus;
	}


	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public LocalDate getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(LocalDate leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public LocalDate getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(LocalDate leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	public String getLeaveDetails() {
		return leaveDetails;
	}

	public void setLeaveDetails(String leaveDetails) {
		this.leaveDetails = leaveDetails;
	}

	public String getLeavePointOfContact() {
		return leavePointOfContact;
	}

	public void setLeavePointOfContact(String leavePointOfContact) {
		this.leavePointOfContact = leavePointOfContact;
	}

	public String getLeavePhoneNumber() {
		return leavePhoneNumber;
	}

	public void setLeavePhoneNumber(String leavePhoneNumber) {
		this.leavePhoneNumber = leavePhoneNumber;
	}

	public ApprovalStatus getLeaveApprovalStatus() {
		return leaveApprovalStatus;
	}

	public void setLeaveApprovalStatus(ApprovalStatus leaveApprovalStatus) {
		this.leaveApprovalStatus = leaveApprovalStatus;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "LeaveApplication [leaveId=" + leaveId + ", leaveType=" + leaveType + ", leaveStartDate="
				+ leaveStartDate + ", leaveEndDate=" + leaveEndDate + ", leaveDetails=" + leaveDetails
				+ ", leavePointOfContact=" + leavePointOfContact + ", leavePhoneNumber=" + leavePhoneNumber
				+ ", leaveApprovalStatus=" + leaveApprovalStatus + ", staff=" + staff + "]";
	}
}
