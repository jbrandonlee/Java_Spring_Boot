package sg.edu.nus.iss.lms.service;

import sg.edu.nus.iss.lms.model.Account;
import sg.edu.nus.iss.lms.model.Employee;

public interface EmployeeService {
	public Employee findEmployeeByAccount(Account account);
}
