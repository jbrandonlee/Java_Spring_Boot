package sg.edu.nus.iss.lms.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Leave {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Employee employee;

	@ManyToOne
	private LeaveType leaveType;
	
	@Transient
	private String leaveTypeString;
	
	private LocalDate startDate;
	
	@Enumerated(EnumType.STRING)
	private DaySection startDaySection;
	
	private LocalDate endDate;
	
	@Enumerated(EnumType.STRING)
	private DaySection endDaySection;
	
	private String reason;
	private String dissemination;
	private String contact;
	
	@Enumerated(EnumType.STRING)
	private LeaveStatus status;
	
	private String managerComment;
	
	public Leave(Employee employee, LeaveType leaveType, LocalDate startDate, DaySection startDaySection, LocalDate endDate, DaySection endDaySection, String reason, String dissemination, String contact, LeaveStatus status) {
		this.employee = employee;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.startDaySection = startDaySection;
		this.endDate = endDate;
		this.endDaySection = endDaySection;
		this.reason = reason;
		this.dissemination = dissemination;
		this.contact = contact;
		this.status = status;
	}

	public double getDuration() {
		double halfDay = (startDaySection == endDaySection) ? 0.5 : 1.0;
		long fullDays = startDate.until(endDate, ChronoUnit.DAYS);
		return fullDays + halfDay;
	}
	
	public enum DaySection {
		AM, PM
	}
	
	public enum LeaveStatus {
		APPLIED, UPDATED, APPROVED, REJECTED, CANCELLED, DELETED
	}
}
