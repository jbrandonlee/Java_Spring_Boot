package sg.nus.iss.javaproject.service;

import java.util.List;

import sg.nus.iss.javaproject.model.LeaveApplication;
import sg.nus.iss.javaproject.model.Staff;
import sg.nus.iss.javaproject.model.StaffRole;

public interface StaffInterface {
	public Staff getStaffByUsername(String username);

}
