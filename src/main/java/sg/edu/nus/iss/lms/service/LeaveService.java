package sg.edu.nus.iss.lms.service;

import java.util.List;

import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.Leave;

public interface LeaveService {
	public Leave createLeave(Leave leave);
	public List<Leave> findEmployeeLeaves(Employee employee);
	public Leave findEmployeeLeaveId(Employee employee, Integer leaveId);
}
