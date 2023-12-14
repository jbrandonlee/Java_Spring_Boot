package sg.edu.nus.iss.javaca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.iss.javaca.model.Leave;
import sg.edu.nus.iss.javaca.model.StatusEnum;


public interface LeaveRepository extends JpaRepository<Leave, Integer> {
	@Query("SELECT l from Leave l WHERE l.employee.managerId = :managerId AND l.status = :status GROUP BY l.employee")
	  List<Leave> findPendingLeaves(@Param("managerId")String managerId, @Param("status")StatusEnum status);
}
