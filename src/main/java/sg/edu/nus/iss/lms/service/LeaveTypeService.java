package sg.edu.nus.iss.lms.service;

import java.util.List;

import sg.edu.nus.iss.lms.model.LeaveType;

public interface LeaveTypeService {
	public LeaveType createLeaveType(LeaveType leaveType);
	public LeaveType updateLeaveType(LeaveType leaveType);
	public List<LeaveType> findAllLeaveTypes();
	public LeaveType findLeaveTypeById(Integer id);
	public List<String> getTypeNames();
	public LeaveType findByType(String type);
}
