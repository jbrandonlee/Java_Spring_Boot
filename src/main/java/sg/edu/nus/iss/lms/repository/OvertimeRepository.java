package sg.edu.nus.iss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.iss.lms.model.Overtime;

public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {
	// Find Overtime ID
	@Query("SELECT o FROM Employee e JOIN e.overtimes o WHERE e.id=:employeeId AND o.id=:overtimeId")
	public Overtime findEmployeeOvertimeById(@Param("employeeId")Integer employeeId, @Param("overtimeId")Integer overtimeId);
		
	// Find All Employee Leaves
	@Query("SELECT o FROM Employee e JOIN e.overtimes o WHERE e.id=:employeeId")
	public List<Overtime> findAllOvertimeByEmployeeId(@Param("employeeId")Integer employeeId);
	
	// -- Manager --
	// Find all Subordinate's Leaves Pending Approve/Reject
	@Query("SELECT o from Employee e JOIN e.overtimes o WHERE e.managerId=:managerId AND o.status IN ('APPLIED', 'UPDATED')")
	List<Overtime> findAllSubordinatePendingOvertimes(@Param("managerId") Integer managerId);
}
