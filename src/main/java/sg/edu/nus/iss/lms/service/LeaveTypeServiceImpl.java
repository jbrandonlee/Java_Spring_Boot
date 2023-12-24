package sg.edu.nus.iss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.model.LeaveType;
import sg.edu.nus.iss.lms.repository.LeaveTypeRepository;

@Service
@Transactional(readOnly = true)
public class LeaveTypeServiceImpl implements LeaveTypeService {
	@Autowired
	LeaveTypeRepository leaveTypeRepo;
	
	@Override
	public LeaveType createLeaveType(LeaveType leaveType) {
		return leaveTypeRepo.saveAndFlush(leaveType);
	}
	
	@Override
	public LeaveType updateLeaveType(LeaveType leaveType) {
		return leaveTypeRepo.saveAndFlush(leaveType);
	}
	
	@Override
	public List<String> getTypeNames() {
		return leaveTypeRepo.findLeaveTypeNames();
	}
	
	@Override
	public LeaveType findByType(String type) {
		return leaveTypeRepo.findByType(type);
	}
	
	@Override
	public List<LeaveType> findAllLeaveTypes() {
		return leaveTypeRepo.findAll();
	}
	
	@Override
	public LeaveType findLeaveTypeById(Integer id) {
		return leaveTypeRepo.findById(id).orElse(null);
	}
}
