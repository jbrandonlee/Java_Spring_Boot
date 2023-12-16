package sg.nus.iss.javaproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

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
    private Date leaveStartDate;
    @NotNull(message = "leaveEndDate can not be empty")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date leaveEndDate;
    @NotBlank(message = "leaveReasons can not be empty")
    private String leaveReasons;
    @NotBlank(message = "workDissemination can not be empty")
    private String workDissemination;
    @NotBlank(message = "destination can not be empty")
    private String destination;
    @Enumerated(EnumType.STRING)
    private ApprovalStatus leaveApprovalStatus;
    @ManyToOne
    @JoinColumn(name="staff_employee_id")
    private Staff staff;
    public LeaveApplication() {
    }

    public LeaveApplication(@NotBlank LeaveType leaveType, Date leaveStartDate, Date leaveEndDate,
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

    public Date getLeaveStartDate() {
        return leaveStartDate;
    }

    public void setLeaveStartDate(Date leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public Date getLeaveEndDate() {
        return leaveEndDate;
    }

    public void setLeaveEndDate(Date leaveEndDate) {
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


    @Override
    public String toString() {
        return "LeaveApplication{" + "leaveId=" + leaveId + ", leaveType=" + leaveType + ", leaveStartDate=" + leaveStartDate + ", leaveEndDate=" + leaveEndDate + ", leaveReasons='" + leaveReasons + '\'' + ", workDissemination='" + workDissemination + '\'' + ", destination='" + destination + '\'' + ", leaveApprovalStatus=" + leaveApprovalStatus + ", staff=" + staff + '}';
    }
}
