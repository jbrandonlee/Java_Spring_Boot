package sg.nus.iss.javaproject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.javaproject.model.LeaveApplication;

public interface LeaveApplicationRepository  extends JpaRepository<LeaveApplication,Integer>{


	 @Query("SELECT lp from LeaveApplication lp WHERE lp.staff.employeeId = :eid  and year(lp.leaveStartDate)=year(now())")
      List<LeaveApplication> findLeaveApplByEID(@Param("eid") int eid);
      
	  @Query("SELECT lp from LeaveApplication lp WHERE lp.staff.employeeId = :eid")
	  Page<LeaveApplication> findLeaveApplByEID(@Param("eid") int eid, Pageable pageable);
}
