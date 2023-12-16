package sg.edu.nus.iss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.Leave;
import sg.edu.nus.iss.lms.repository.LeaveRepository;

@Service
@Transactional(readOnly = true)
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	LeaveRepository leaveRepo;
	
	@Override
	public Leave createLeave(Leave leave) {
		return leaveRepo.saveAndFlush(leave);
	}
	
	@Override
	public Leave updateLeave(Leave leave) {
		return leaveRepo.saveAndFlush(leave);
	}
	
	@Override
	public List<Leave> findEmployeeLeaves(Employee employee) {
		return leaveRepo.findAllLeaveByEmployeeId(employee.getId());
	}
	
	@Override
	public Leave findEmployeeLeaveId(Employee employee, Integer leaveId) {
		return leaveRepo.findEmployeeLeaveByIds(employee.getId(), leaveId);
	}
}
