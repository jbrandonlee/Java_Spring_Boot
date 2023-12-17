package sg.edu.nus.iss.lms.service;

import java.util.List;

import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.Leave;

public interface LeaveService {
	public Leave createLeave(Leave leave);
	public Leave updateLeave(Leave leave);
	public List<Leave> findAllEmployeeLeaves(Employee employee);
	public List<Leave> findEmployeeLeavesCurrYear(Employee employee);
	public List<Leave> findEmployeeLeavesUpcoming(Employee employee);
	public Leave findEmployeeLeaveId(Employee employee, Integer leaveId);
}
