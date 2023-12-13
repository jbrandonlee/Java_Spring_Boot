package sg.nus.iss.javaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.iss.javaproject.model.Staff;
import sg.nus.iss.javaproject.repository.StaffRepository;

@Service
@Transactional
public class StaffImplementation implements StaffInterface {
	@Autowired
	StaffRepository staffRepo;

	@Override
	public Staff getStaffByName(String username) {
		return  staffRepo.getByName(username);
	}
}
