package sg.edu.nus.iss.lms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LeaveEntitlement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private LeaveType leaveType;
	
	@NotNull(message = "Assigned Leave Entitlement must not be empty.")
	private double entitlement;
	
	@NotNull(message = "Assigned Leave Balance must not be empty.")
	private double leaveBalance;
	
	@ManyToOne
	private Employee employee;
	
	// TestData Constructor
	public LeaveEntitlement(Employee employee, LeaveType leaveType, double entitlement) {
		this.employee = employee;
		this.leaveType = leaveType;
		this.entitlement = entitlement;
		this.leaveBalance = entitlement;
	}
}
