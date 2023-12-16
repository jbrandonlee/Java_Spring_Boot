package sg.edu.nus.iss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.iss.lms.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {
	@Query("SELECT l FROM Employee e JOIN e.leaves l WHERE e.id=:employeeId")
	public List<Leave> findAllLeaveByEmployeeId(@Param("employeeId")Integer employeeId);
	
	@Query("SELECT l FROM Employee e JOIN e.leaves l WHERE e.id=:employeeId AND l.id=:leaveId")
	public Leave findEmployeeLeaveByIds(@Param("employeeId")Integer employeeId, @Param("leaveId")Integer leaveId);
}
