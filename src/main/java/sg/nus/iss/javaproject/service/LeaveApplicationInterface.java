package sg.nus.iss.javaproject.service;

import java.util.List;

import jakarta.validation.Valid;
import sg.nus.iss.javaproject.model.LeaveApplication;

public interface LeaveApplicationInterface {

	public void saveLeaveApplication(LeaveApplication leaveApplication);

	public List<LeaveApplication> getAllLeaveByStaffId(int id);

	public LeaveApplication findLeaveById(Integer id);

}
