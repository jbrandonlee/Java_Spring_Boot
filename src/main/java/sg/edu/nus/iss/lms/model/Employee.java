package sg.edu.nus.iss.lms.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	private String name;
	
	private String jobDesignation;
	
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