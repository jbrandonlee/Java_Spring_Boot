package sg.edu.nus.iss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.model.Account;
import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.repository.EmployeeRepository;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepo;

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepo.saveAndFlush(employee);
	}
	
	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepo.saveAndFlush(employee);
	}
	
	@Override
	public List<Employee> findAllEmployees() {
		return employeeRepo.findAll();
	}
	
	@Override
	public Employee findEmployeeById(Integer id) {
		return employeeRepo.findById(id).orElse(null);
	}
	
	@Override
	public Employee findEmployeeByAccount(Account account) {
		return employeeRepo.findByAccountUsername(account.getUsername());
	}
	
	@Override
	public List<Employee> findAllSubordinates(Employee manager) {
    	return employeeRepo.findAllSubordinates(manager.getId());
    }
	
	@Override
	public List<Integer> findAllEmployeeIDs() {
		return employeeRepo.findAllEmployeeIDs();
	}
	
	@Override
	public List<Integer> findAllEmployeeIDNoAccount() {
		return employeeRepo.findAllEmployeeIDNoAccount();
	}
	
	@Override
	public List<Integer> findAllManagerIDs() {
		return employeeRepo.findAllManagerIDs();
	}
}
