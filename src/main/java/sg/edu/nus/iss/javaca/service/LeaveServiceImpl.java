package sg.edu.nus.iss.javaca.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.javaca.model.Leave;
import sg.edu.nus.iss.javaca.model.StatusEnum;
import sg.edu.nus.iss.javaca.repository.LeaveRepository;

@Service
@Transactional(readOnly=true)
public class LeaveServiceImpl implements LeaveService{
	
	@Autowired
	private LeaveRepository leaveRepo;

	@Override
	@Transactional
	public List<Leave> findPendingLeaves(String managerId, StatusEnum status) {
		
		return leaveRepo.findPendingLeaves(managerId,status);
	}

}
