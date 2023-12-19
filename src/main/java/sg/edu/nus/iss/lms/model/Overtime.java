package sg.edu.nus.iss.lms.model;

import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Overtime {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Employee employee;
	
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String reason;
	
	public Overtime(Employee employee, LocalDateTime startTime, LocalDateTime endTime, String reason) {
		this.employee = employee;
		this.startTime = startTime;
		this.endTime = endTime;
		this.reason = reason;
	}
	
	public double getClaimableCompensation() {
		long hours = Duration.between(startTime, endTime).toHours();
		double compensationClaim = (hours % 4) * 0.5;
		return compensationClaim;
	}
}
