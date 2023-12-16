package sg.edu.nus.iss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.iss.lms.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}