package sg.edu.nus.iss.lms.service;

import java.util.List;

import sg.edu.nus.iss.lms.model.Account;
import sg.edu.nus.iss.lms.model.Employee;

public interface EmployeeService {
	public List<Employee> findAllEmployees();
	public Employee findEmployeeById(Integer id);
	public Employee findEmployeeByAccount(Account account);
	public List<Employee> findAllSubordinates(Employee manager);
	public List<Integer> findAllEmployeeIDs();
	public List<Integer> findAllEmployeeIDNoAccount();
	public List<Integer> findAllManagerIDs();
}
