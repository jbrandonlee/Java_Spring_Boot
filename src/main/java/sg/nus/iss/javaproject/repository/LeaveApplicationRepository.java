package sg.nus.iss.javaproject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.javaproject.model.LeaveApplication;

public interface LeaveApplicationRepository  extends JpaRepository<LeaveApplication,Integer>{

//	@Query("SELECT lp from LeaveApplication lp WHERE lp.staffEmployeeId = :eid")
//	  List<LeaveApplication> findLeaveApplByEID(@Param("eid") int eid);
//
	 @Query("SELECT lp from LeaveApplication lp WHERE lp.staff.employeeId = :eid  and year(lp.leaveStartDate)=year(now())")
      List<LeaveApplication> findLeaveApplByEID(@Param("eid") int eid);
//
   // @Query("SELECT lp from LeaveApplication lp WHERE lp.staffEmployeeId = :eid AND (lp.leaveApprovalStatus ='APPLIED' OR lp.leaveApprovalStatus ='UPDATED')")
	  //List<LeaveApplication> findPendingLeaveApplByEID(@Param("eid") int eid);
//
//	  @Query(value = "SELECT * FROM leaveApplication WHERE status = ?0", nativeQuery = true)
//	  List<leaveApplication> findPendingLeaveApplByStatus(String status);
}
