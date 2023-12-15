package sg.nus.iss.javaproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.iss.javaproject.model.LeaveApplication;
import sg.nus.iss.javaproject.repository.LeaveApplicationRepository;

@Service
@Transactional
public class LeaveApplicationImplementation implements LeaveApplicationInterface {
	@Autowired
	LeaveApplicationRepository lRepo;
	
	@Override
	public void saveLeaveApplication(LeaveApplication leaveApplication) {
		lRepo.save(leaveApplication);
	}
	
	@Override
	public List<LeaveApplication> getAllLeaveByStaffId(int id){
		return lRepo.getAllLeave(id);
	}
	
	@Override
	public LeaveApplication findLeaveById(Integer id) {
		return lRepo.findById(id).get();
	}
}
