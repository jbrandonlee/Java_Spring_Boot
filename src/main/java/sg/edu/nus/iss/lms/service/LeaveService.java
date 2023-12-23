package sg.edu.nus.iss.lms.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.nus.iss.lms.model.ApprovalStatus;
import sg.edu.nus.iss.lms.model.LeaveApplication;

public interface LeaveService {
	
    List<LeaveApplication> findLeaveApplicationByEID(int employeeId);
    
    LeaveApplication applyLeaveApplication(LeaveApplication leaveApplication);

    LeaveApplication editLeaveApplication(LeaveApplication leaveApplication);

    void removeLeaveApplication(LeaveApplication leaveApplication);

    Page<LeaveApplication> findLeaveApplicationPage(int pageNo, int pageSize, String sortField, String sortDirection);
	
	List<LeaveApplication> findAllLeaves();
	
	List<LeaveApplication> findPendingLeaves(int managedBy, ApprovalStatus leaveApprovalStatus);
	
	List<LeaveApplication> findLeavesHistory(int managedBy, ApprovalStatus leaveApprovalStatus);
	
	void save(LeaveApplication leaveApplication);
	
	LeaveApplication findByLeaveId(int id);
	
	List<LeaveApplication> getOverlappingLeaves(LocalDate startDate, LocalDate endDate);

}
