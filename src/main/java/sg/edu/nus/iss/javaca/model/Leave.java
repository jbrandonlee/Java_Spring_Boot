package sg.edu.nus.iss.javaca.model;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;

@Entity
@Table(name = "EmpLeave")
public class Leave {
	
	//attributes
	@Id
	@Column(name = "EmpLeaveId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int leaveId;
	
	@Column(name = "LeaveType")
	private String leaveType;
	
	@Column(name = "StartDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@Column(name = "EndDate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	@Column(name = "Reason")
	private String reason;
	
	@Column(name = "EmpContactNumber")
	private String contactNumber;
	
	@Column(name = "Comment")
	private String comment;
	
	@Column(name = "Status", columnDefinition = "ENUM('Applied', 'Updated', 'Approved', 'Rejected', 'Cancelled', 'Deleted')")
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	
	@ManyToOne
	@JoinColumn(name = "EmployeeId")
	private Employee employee;

	public int getLeaveId() {
		return leaveId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
