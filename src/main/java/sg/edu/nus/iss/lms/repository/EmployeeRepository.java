package sg.edu.nus.iss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.iss.lms.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	@Query("SELECT e FROM Account a JOIN a.employee e WHERE a.username=:username")
	public Employee findByAccountUsername(@Param("username")String username);
	
	// Find all Subordinates
	@Query("SELECT e from Employee e WHERE e.managerId=:managerId")
	public List<Employee> findAllSubordinates(@Param("managerId") Integer managerId);
	
	@Query("SELECT e.id FROM Employee e")
	public List<Integer> findAllEmployeeIDs();
	
	@Query("SELECT e.id FROM Employee e WHERE e.id NOT IN (SELECT a.employee.id FROM Account a WHERE a.employee.id IS NOT NULL)")
	public List<Integer> findAllEmployeeIDNoAccount();

	@Query("SELECT e.id FROM Account a JOIN a.employee e JOIN a.roles r WHERE r.id='manager'")
	public List<Integer> findAllManagerIDs();
}
