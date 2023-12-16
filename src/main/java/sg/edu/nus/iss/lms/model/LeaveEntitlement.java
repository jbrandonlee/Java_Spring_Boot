package sg.edu.nus.iss.lms.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class LeaveEntitlement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private LeaveType leaveType;
	private Integer leaveEntitlement;
	private Integer leaveBalamce;
	
	@ManyToOne
	private Employee employee;
}
