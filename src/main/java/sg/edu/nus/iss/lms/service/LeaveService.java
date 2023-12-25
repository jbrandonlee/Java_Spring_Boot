package sg.edu.nus.iss.lms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.LeaveApplication;

public interface LeaveService {
	// -- Employee --
	public LeaveApplication createLeave(LeaveApplication leave);
	public LeaveApplication updateLeave(LeaveApplication leave);
	public List<LeaveApplication> findAllEmployeeLeaves(Employee employee);
	public List<LeaveApplication> findEmployeeLeavesCurrYear(Employee employee);
	public List<LeaveApplication> findEmployeeLeavesUpcoming(Employee employee);
	public LeaveApplication findEmployeeLeaveId(Employee employee, Integer leaveId);

	
	// -- Manager --
	public List<LeaveApplication> findAllSubordinatePendingLeaves(Employee manager);
	public List<LeaveApplication> findOverlappingSubordinateLeaves(Employee manager, LeaveApplication leave);
	public List<LeaveApplication> findSubordinateLeaveHistory(Employee manager, Integer subordinateId);
	public LeaveApplication findSubordinateLeaveById(Employee manager, Integer subordinateId, Integer leaveId);
	
	// -- Utility --
	public Page<LeaveApplication> getPaginatedLeaves(int page, int pageSize, List<LeaveApplication> listLeaves);
	public double calculateDeductibleDaysInLeave(LeaveApplication leave);
}
