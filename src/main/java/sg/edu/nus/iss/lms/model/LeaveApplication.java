package sg.edu.nus.iss.lms.model;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveId;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "leaveType can not be empty")
    private LeaveType leaveType;
    
    @NotNull(message = "leaveStartDate can not be empty")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate leaveStartDate;
    
    @NotNull(message = "leaveEndDate can not be empty")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate leaveEndDate;
    
    @NotBlank(message = "leaveReasons can not be empty")
    private String leaveReasons;
    
    @NotBlank(message = "workDissemination can not be empty")
    private String workDissemination;
    
    @NotBlank(message = "destination can not be empty")
    private String destination;
    
    private String leavePhoneNumber;
    
    @Column(name = "managerComment")
    private String managerComment;
    
    @Enumerated(EnumType.STRING)
    private ApprovalStatus leaveApprovalStatus;
    
    @ManyToOne
    @JoinColumn(name="staff_employee_id")
    private Staff staff;
    
    public LeaveApplication() {
    }

    public LeaveApplication(LeaveType leaveType, LocalDate leaveStartDate, LocalDate leaveEndDate,
        String leaveReasons, String workDissemination) {
        super();
        this.leaveType = leaveType;
        this.leaveStartDate = leaveStartDate;
        this.leaveEndDate = leaveEndDate;
        this.leaveReasons = leaveReasons;
        this.workDissemination = workDissemination;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getLeaveReasons() {
        return leaveReasons;
    }

    public void setLeaveReasons(String leaveReasons) {
        this.leaveReasons = leaveReasons;
    }

    public String getWorkDissemination() {
        return workDissemination;
    }

    public void setWorkDissemination(String workDissemination) {
        this.workDissemination = workDissemination;
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


    public long calculateDaysDiff() {
    	Period period = Period.between(leaveStartDate, leaveEndDate);
    	return period.getDays();
    }

	public String getManagerComment() {
		return managerComment;
	}

	public void setManagerComment(String managerComment) {
		this.managerComment = managerComment;
	}

	public String getLeavePhoneNumber() {
		return leavePhoneNumber;
	}

	public void setLeavePhoneNumber(String leavePhoneNumber) {
		this.leavePhoneNumber = leavePhoneNumber;
	}

	@Override
	public String toString() {
	    return "LeaveApplication [leaveId=" + leaveId + ", leaveType=" + leaveType + ", leaveStartDate="
	            + leaveStartDate + ", leaveEndDate=" + leaveEndDate + ", leaveReasons=" + leaveReasons
	            + ", workDissemination=" + workDissemination + ", destination=" + destination + ", leavePhoneNumber="
	            + leavePhoneNumber + ", managerComment=" + managerComment + ", leaveApprovalStatus="
	            + leaveApprovalStatus + ", staff=" + (staff != null ? staff.getEmployeeId() : "null") + "]";
	}

}
