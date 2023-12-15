package sg.nus.iss.javaproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.javaproject.model.LeaveApplication;

public interface LeaveApplicationRepository  extends JpaRepository<LeaveApplication,Integer>{
	@Query("SELECT l FROM LeaveApplication l JOIN l.staff s WHERE s.employeeId=:id ")
	public List<LeaveApplication> getAllLeave(@Param("id") int id);
}
