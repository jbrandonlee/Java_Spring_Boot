package sg.edu.nus.iss.lms.service;

import java.util.List;

import sg.edu.nus.iss.lms.model.Staff;

public interface StaffService {
	
	public Staff getStaffByUsername(String username);
	
	List<Staff> findSubordinates(int employeeId);

	public Staff findStaffById(int staffId);

}
