package sg.edu.nus.iss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.repository.LeaveTypeRepository;

@Service
@Transactional(readOnly = true)
public class LeaveTypeServiceImpl implements LeaveTypeService {
	@Autowired
	LeaveTypeRepository leaveTypeRepo;
	
	public List<String> getTypeNames() {
		return leaveTypeRepo.findLeaveTypeNames();
	}
}
