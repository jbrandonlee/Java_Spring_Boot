package sg.edu.nus.iss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.edu.nus.iss.lms.model.Staff;



public interface StaffRepository extends JpaRepository<Staff, Integer>{
	
	@Query("Select s from Staff s JOIN s.account a where a.username=:username")
	public Staff getStaffByUsername(@Param("username")String username);
	
	@Query("SELECT DISTINCT s2 FROM Staff s1, Staff s2 WHERE s1.employeeId = s2.managedBy AND s1.employeeId = :eid")
	List<Staff> findSubordinates(@Param("eid") int eid);

}
