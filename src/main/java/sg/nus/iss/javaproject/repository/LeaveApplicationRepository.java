package sg.nus.iss.javaproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.javaproject.model.LeaveApplication;

public interface LeaveApplicationRepository  extends JpaRepository<LeaveApplication,Integer>{

	@Query("SELECT lp from LeaveApplication lp WHERE lp.employeeId = :eid")
	  List<LeaveApplication> findLeaveApplByEID(@Param("eid") int eid);
//
//	  @Query("SELECT lp from leaveApplication lp WHERE lp.employeeId = :eid")
//	  Page<leaveApplication> findLeaveApplByEID(@Param("eid") int eid, Pageable pageable);
//
	  @Query("SELECT lp from LeaveApplication lp WHERE lp.employeeId = :eid AND (lp.leaveApprovalStatus ='APPLIED' OR lp.leaveApprovalStatus ='UPDATED')")
	  List<LeaveApplication> findPendingLeaveApplByEID(@Param("eid") int eid);
//
//	  @Query(value = "SELECT * FROM leaveApplication WHERE status = ?0", nativeQuery = true)
//	  List<leaveApplication> findPendingLeaveApplByStatus(String status);
}
