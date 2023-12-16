package sg.edu.nus.iss.lms.service;

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
	public Employee findEmployeeByAccount(Account account) {
		return employeeRepo.findByAccountUsername(account.getUsername());
	}
	
}
