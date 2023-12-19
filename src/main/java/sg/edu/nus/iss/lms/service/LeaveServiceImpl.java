package sg.edu.nus.iss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.nus.iss.lms.model.ApprovalStatus;
import sg.edu.nus.iss.lms.model.LeaveApplication;
import sg.edu.nus.iss.lms.repository.LeaveApplicationRepository;

@Service
@Transactional
public class LeaveServiceImpl implements LeaveService{
	
	@Autowired
	private LeaveApplicationRepository leaveRepo;
	
	@Override
	public List<LeaveApplication> findAllLeaves() {
		
		return leaveRepo.findAll();
	}

	@Override
	@Transactional
	public List<LeaveApplication> findPendingLeaves(int managedBy, ApprovalStatus leaveApprovalStatus) {
		
		return leaveRepo.findPendingLeaves(managedBy, leaveApprovalStatus);
	}
	
	@Override
	public LeaveApplication applyLeaveApplication(LeaveApplication leaveApplication) {
		return leaveRepo.save(leaveApplication);
	}
	
	@Override
	public List<LeaveApplication> findLeaveApplicationByEID(int employeeId) {
		return leaveRepo.findLeaveApplByEID(employeeId);
	}

	@Override
	@Transactional
	public LeaveApplication editLeaveApplication(LeaveApplication leaveApplication) {
		return leaveRepo.saveAndFlush(leaveApplication);
	}

	@Override
	public void removeLeaveApplication(LeaveApplication leaveApplication) {
		leaveRepo.delete(leaveApplication);
	}

	@Override
	public Page<LeaveApplication> findLeaveApplicationPage(int pageNo, int pageSize, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return leaveRepo.findAll(pageable);
	}

	@Override
	public List<LeaveApplication> findLeavesHistory(int managedBy, ApprovalStatus leaveApprovalStatus) {
		
		return leaveRepo.findLeavesHistory(managedBy, leaveApprovalStatus);
	}

	@Override
	public void save(LeaveApplication leaveApplication) {
		
		leaveRepo.save(leaveApplication);
	}

	@Override
	public LeaveApplication findByLeaveId(int id) {
		
		return leaveRepo.findById(id).get();
	}

}
