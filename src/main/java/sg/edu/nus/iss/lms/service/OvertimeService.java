package sg.edu.nus.iss.lms.service;

import java.util.List;

import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.Overtime;

public interface OvertimeService {
	public List<Overtime> findAllEmployeeOvertime(Employee employee);
	public Overtime findEmployeeOvertimeId(Employee employee, Integer leaveId);
	public List<Overtime> findAllSubordinatePendingOvertimes(Employee manager);
}
