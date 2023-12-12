package LAPRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import LAPModels.leaveApplication;

@Service
public interface leaveRepository extends JpaRepository <leaveApplication, Integer>  {
	  
	@Query("SELECT l from leaveApplication l WHERE l.employeeId = :eid")
	  List<leaveApplication> findLeaveApplByEID(@Param("eid") String eid);
	  
	  @Query("SELECT l from Course c WHERE c.employeeId = :eid AND (c.status ='SUBMITTED' OR c.status ='UPDATED')")
	  List<leaveApplication> findPendingLeaveApplByEID(@Param("eid") String eid);
	  
	  @Query(value = "SELECT * FROM course WHERE status = ?0", nativeQuery = true)
	  List<leaveApplication> findPendingLeaveApplByStatus(String status);
}
