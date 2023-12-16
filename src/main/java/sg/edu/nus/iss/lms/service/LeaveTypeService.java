package sg.edu.nus.iss.lms.service;

import java.util.List;

import sg.edu.nus.iss.lms.model.LeaveType;

public interface LeaveTypeService {
	public List<String> getTypeNames();
	public LeaveType findByType(String type);
}
