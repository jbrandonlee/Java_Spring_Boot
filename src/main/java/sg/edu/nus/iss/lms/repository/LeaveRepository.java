package sg.edu.nus.iss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.iss.lms.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {
	//@Query("SELECT l FROM Employee e JOIN e.myLeaves l WHERE e.id = userId AND l.id=:leaveId")
	//public Leave findEmployeeLeaveById(@Param("userId")String userId, @Param("leaveId")Integer leaveId);
}
