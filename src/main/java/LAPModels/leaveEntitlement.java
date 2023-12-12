package LAPModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity 
public class leaveEntitlement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	

	private String employeeDesignation;
	private int annualLeaveDays;
	private int medicalLeaveDays;
	private int compensationLeaveDays;
	
	@OneToOne(mappedBy = "leaveEtt")
	private employee employee;

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	public int getAnnualLeaveDays() {
		return annualLeaveDays;
	}

	public void setAnnualLeaveDays(int annualLeaveDays) {
		this.annualLeaveDays = annualLeaveDays;
	}

	public int getMedicalLeaveDays() {
		return medicalLeaveDays;
	}

	public void setMedicalLeaveDays(int medicalLeaveDays) {
		this.medicalLeaveDays = medicalLeaveDays;
	}

	public int getCompensationLeaveDays() {
		return compensationLeaveDays;
	}

	public void setCompensationLeaveDays(int compensationLeaveDays) {
		this.compensationLeaveDays = compensationLeaveDays;
	}
}
