package sg.edu.nus.iss.lms.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	
	private Integer managerId;
	
	@ManyToOne
	private Department department;
	
	@OneToMany(mappedBy="employee")
	private List<Leave> leaves;
	
	public Employee(String name, String jobDesignation, Department department) {
		this.name = name;
		this.jobDesignation = jobDesignation;
		this.managerId = null;
		this.department = department;
		this.leaves = new ArrayList<Leave>();
	}
}