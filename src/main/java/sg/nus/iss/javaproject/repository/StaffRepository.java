package sg.nus.iss.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.javaproject.model.Staff;

public interface StaffRepository extends JpaRepository<Staff,Integer> {
	@Query("Select s from Staff as s where s.employeeName=:username")
	public Staff getByName(@Param("username")String username);
}

