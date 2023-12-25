package sg.edu.nus.iss.lms.model;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OvertimeClaim {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Employee employee;
	
	@Past(message="Start Time must be before today.")
	private LocalDateTime startTime;
	
	@Past(message="End Time must be before today.")
	private LocalDateTime endTime;
	
	@NotBlank(message="Reason for overtime claim is required.")
	private String reason;
	
	@Enumerated(EnumType.STRING)
	private ClaimStatus claimStatus;
	
	private String managerComment;
	
	public OvertimeClaim(Employee employee, LocalDateTime startTime, LocalDateTime endTime, String reason, ClaimStatus claimStatus) {
		this.employee = employee;
		this.startTime = startTime;
		this.endTime = endTime;
		this.reason = reason;
		this.claimStatus = claimStatus;
	}
	
	public double getDuration() {
		long halfHoursFloored = Duration.between(startTime, endTime).toMinutes() / 30;
		return halfHoursFloored / 2.0;
	}
	
	public double getClaimableCompensation() {
		return (Duration.between(startTime, endTime).toHours() / 4) * 0.5;
	}
	
	public enum ClaimStatus {
		APPLIED, UPDATED, APPROVED, REJECTED, DELETED
	}
}
