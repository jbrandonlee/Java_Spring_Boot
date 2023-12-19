package sg.edu.nus.iss.lms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.edu.nus.iss.lms.model.ApprovalStatus;
import sg.edu.nus.iss.lms.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {
	@Query("SELECT l from LeaveApplication l WHERE l.staff.managedBy = :managedBy AND l.leaveApprovalStatus = :status ORDER BY l.staff.employeeId ASC")
	List<LeaveApplication> findPendingLeaves(@Param("managedBy")int managedBy, @Param("status")ApprovalStatus leaveApprovalStatus);
	
	@Query("SELECT l from LeaveApplication l WHERE l.staff.managedBy = :managedBy AND l.leaveApprovalStatus != :status")
	List<LeaveApplication> findLeavesHistory(@Param("managedBy")int managedBy, @Param("status")ApprovalStatus leaveApprovalStatus);
	
	@Query("SELECT l from LeaveApplication l WHERE l.staff.employeeId = :eid  and year(l.leaveStartDate)=year(now())")
    List<LeaveApplication> findLeaveApplByEID(@Param("eid") int eid);
	
	
}
