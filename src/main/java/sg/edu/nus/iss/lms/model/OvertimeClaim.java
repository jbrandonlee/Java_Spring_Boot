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
	
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String reason;
	
	@Enumerated(EnumType.STRING)
	private ClaimStatus status;
	
	public OvertimeClaim(Employee employee, LocalDateTime startTime, LocalDateTime endTime, String reason, ClaimStatus status) {
		this.employee = employee;
		this.startTime = startTime;
		this.endTime = endTime;
		this.reason = reason;
		this.status = status;
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
