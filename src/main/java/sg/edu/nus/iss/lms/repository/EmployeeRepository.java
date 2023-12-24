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
	public List<String> findAllEmployeeIDs();
	
	@Query("SELECT DISTINCT m FROM Employee e, Employee m where e.managerId = m.id ")
	public List<Employee> findAllManagers();
}
