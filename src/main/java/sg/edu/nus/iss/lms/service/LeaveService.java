package sg.edu.nus.iss.lms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.Leave;

public interface LeaveService {
	// -- Employee --
	public Leave createLeave(Leave leave);
	public Leave updateLeave(Leave leave);
	public List<Leave> findAllEmployeeLeaves(Employee employee);
	public List<Leave> findEmployeeLeavesCurrYear(Employee employee);
	public List<Leave> findEmployeeLeavesUpcoming(Employee employee);
	public Leave findEmployeeLeaveId(Employee employee, Integer leaveId);
	public Page<Leave> getPaginatedLeaves(int page, int pageSize, List<Leave> listLeaves);
	
	// -- Manager --
	public List<Leave> findAllSubordinatePendingLeaves(Employee manager);
	public List<Leave> findAllSubordinateLeaveHistoryInDuration(Employee manager, Leave leave);
	public List<Leave> findSubordinateLeaveHistory(Employee manager, Integer subordinateId);
	public Leave findSubordinateLeaveById(Employee manager, Integer subordinateId, Integer leaveId);
}
