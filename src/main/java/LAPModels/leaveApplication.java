
package LAPModels;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class leaveApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int leaveAplId;
	private int employeeId;
	
	private String leaveType;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	private String leaveDetails;
	
	@Column
	private String leavePointOfContact;
	
	@Column
	private String leavePhoneNumber;
	
	@Column(name = "status", columnDefinition = "ENUM('APPLIED', 'APPROVED', 'CANCELLED', 'DELETED', 'UPDATED', 'REJECTED')")
	@Enumerated(EnumType.STRING)
	private leaveApprovalStatus leaveAprStatus;
	
	 private String comment;
	 
	 @ManyToOne
	 private employee employee;
	 
	 public leaveApplication() {};
	 public leaveApplication(int leaveAplId, int employeeId, String leaveType, LocalDate startDate, LocalDate endDate,
		  		String leaveDetails, String leavePointOfContact, String leavePhoneNumber,
		  		leaveApprovalStatus leaveAprStatus, String comment) {
 this.employeeId = employeeId;
 this.leaveType = leaveType;
 this.startDate = startDate;
 this.endDate = endDate;
 this.leaveDetails = leaveDetails;
 this.leavePointOfContact= leavePointOfContact;
 this.leavePhoneNumber=leavePhoneNumber;
 this.leaveAprStatus= leaveAprStatus;
 this.comment=comment;
}
	

	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getLeaveDetails() {
		return leaveDetails;
	}
	public void setLeaveDetails(String leaveDetails) {
		this.leaveDetails = leaveDetails;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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
	public leaveApprovalStatus getLeavestatus() {
		return leaveAprStatus;
	}
	public void setLeavestatus(leaveApprovalStatus leaveAprStatus) {
		this.leaveAprStatus = leaveAprStatus;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public int getLeaveAplId() {
		return leaveAplId;
	}

	public void setLeaveAplId(int leaveAplId) {
		this.leaveAplId = leaveAplId;
	}
	
	
}
