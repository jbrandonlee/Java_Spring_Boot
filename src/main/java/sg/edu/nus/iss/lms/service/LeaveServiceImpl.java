package sg.edu.nus.iss.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.model.Leave;
import sg.edu.nus.iss.lms.repository.LeaveRepository;

@Service
@Transactional(readOnly = true)
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	LeaveRepository leaveRepo;
	
	@Override
	public Leave createLeave(Leave leave) {
		return leaveRepo.save(leave);
	};
	/*
	@Override
	public Leave findEmployeeLeaveById(String employeeId, Integer leaveId) {
		return leaveRepo.findEmployeeLeaveById(employeeId, leave);
	}*/

}
