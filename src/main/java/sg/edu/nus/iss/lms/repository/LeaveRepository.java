package sg.edu.nus.iss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.iss.lms.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {
	// Find Leave ID
	@Query("SELECT l FROM Employee e JOIN e.leaves l WHERE e.id=:employeeId AND l.id=:leaveId")
	public Leave findEmployeeLeaveById(@Param("employeeId")Integer employeeId, @Param("leaveId")Integer leaveId);
	
	// Find All Employee Leaves
	@Query("SELECT l FROM Employee e JOIN e.leaves l WHERE e.id=:employeeId")
	public List<Leave> findAllLeaveByEmployeeId(@Param("employeeId")Integer employeeId);
	
	// Find Employee Non-Cancelled Non-Deleted Leaves in Current Year
	@Query("SELECT l FROM Employee e JOIN e.leaves l WHERE e.id=:employeeId AND YEAR(l.startDate)=YEAR(NOW()) AND l.status NOT IN ('CANCELLED', 'DELETED')")
	public List<Leave> findCurrYearLeaveByEmployeeId(@Param("employeeId")Integer employeeId);
	
	// Find Employee Non-Cancelled Non-Deleted Upcoming Leaves
	@Query("SELECT l FROM Employee e JOIN e.leaves l WHERE e.id=:employeeId AND l.startDate >= NOW() AND l.status NOT IN ('CANCELLED', 'DELETED')")
	public List<Leave> findUpcomingLeaveByEmployeeId(@Param("employeeId")Integer employeeId);
	
}
