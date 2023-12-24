package sg.edu.nus.iss.lms.service;

import java.util.List;

import sg.edu.nus.iss.lms.model.Account;
import sg.edu.nus.iss.lms.model.Employee;

public interface EmployeeService {
	public Employee findEmployeeById(Integer id);
	public Employee findEmployeeByAccount(Account account);
	public List<Employee> findAllSubordinates(Employee manager);
	public List<Employee> findAllEmployees();
}
