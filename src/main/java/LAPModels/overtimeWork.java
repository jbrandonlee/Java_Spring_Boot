package LAPModels;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class overtimeWork {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)	
 
 private int employeeId;
 
 @Column
 @DateTimeFormat(pattern = "yyyy-MM-dd")
 private LocalDate overtimeDate;
 
 @Column
 @DateTimeFormat(pattern = "yyyy-MM-dd")
 private LocalDate overtimeStartTime;
 
 @Column
 @DateTimeFormat(pattern = "yyyy-MM-dd")
 private LocalDate overtimeEndTime;
 
 @Column
 private String compensationEntitlement;

 @ManyToOne
 private employee employee;
 
 public int getEmployeeId() {
	return employeeId;
}

 public void setEmployeeId(int employeeId) {
	this.employeeId = employeeId;
}

 public LocalDate getOvertimeStartTime() {
	return overtimeStartTime;
}

 public void setOvertimeStartTime(LocalDate overtimeStartTime) {
	this.overtimeStartTime = overtimeStartTime;
}

 public LocalDate getOvertimeEndTime() {
	return overtimeEndTime;
}

 public void setOvertimeEndTime(LocalDate overtimeEndTime) {
	this.overtimeEndTime = overtimeEndTime;
}

 public String getCompensationEntitlement() {
	return compensationEntitlement;
}

 public void setCompensationEntitlement(String compensationEntitlement) {
	this.compensationEntitlement = compensationEntitlement;
}
 
  public overtimeWork() {};
  public overtimeWork(int employeeId, LocalDate overtimeDate, 
		  LocalDate overtimeStartTime, 
		  LocalDate overtimeEndTime, String compensationEntitlement ) {
	  
	  this.employeeId=employeeId;
	  this.overtimeDate=overtimeDate;
	  this.overtimeStartTime=overtimeStartTime;
	  this.overtimeEndTime=overtimeEndTime;
	  this.compensationEntitlement=compensationEntitlement;
  }
}
