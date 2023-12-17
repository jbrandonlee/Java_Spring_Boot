package sg.edu.nus.iss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.nus.iss.lms.model.Staff;
import sg.edu.nus.iss.lms.repository.StaffRepository;

@Service
@Transactional(readOnly=true)
public class StaffServiceImpl implements StaffService{
	
	@Autowired
	StaffRepository staffRepo;
	
	@Override
	public Staff getStaffByUsername(String username) {
		
		return staffRepo.getStaffByUsername(username);
	}

	@Override
	public List<Staff> findSubordinates(int employeeId) {
		
		return staffRepo.findSubordinates(employeeId);
	}

	@Override
	public Staff findStaffById(int staffId) {
		
		return staffRepo.findById(staffId).orElse(null);
	}

}
