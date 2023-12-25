package sg.edu.nus.iss.lms.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Name must not be empty.")
	private String name;
	
	@NotBlank(message = "Job Designation must not be empty.")
	private String jobDesignation;
	
	@NotNull(message = "Join Date must not be empty.")
	private LocalDate joinDate;
	
	private Integer managerId;
	
	@OneToMany(mappedBy="employee")
	private List<LeaveApplication> leaves;
	
	@OneToMany(mappedBy="employee")
	private List<LeaveEntitlement> leaveEntitlements;
	
	@OneToMany(mappedBy="employee")
	private List<OvertimeClaim> overtimes;
	
	public Employee(String name, String jobDesignation, LocalDate joinDate, Integer managerId) {
		this.name = name;
		this.jobDesignation = jobDesignation;
		this.joinDate = joinDate;
		this.managerId = managerId;
		this.leaves = new ArrayList<LeaveApplication>();
		this.leaveEntitlements = new ArrayList<LeaveEntitlement>();
		this.overtimes = new ArrayList<OvertimeClaim>();
	}
}