package sg.nus.iss.javaproject.service;

import java.util.List;

import sg.nus.iss.javaproject.model.LeaveApplication;

public interface LeaveApplicationInterface {
	List<LeaveApplication> findAllLeaveApplication();
	 List<LeaveApplication> findLeaveApplicationByEID(int employeeId);
	 
	 LeaveApplication findLeaveApplicaiton(Integer employeeId);

	 
	 LeaveApplication editLeaveApplication(LeaveApplication leaveApplication);

	 void removeLeaveApplication(LeaveApplication leaveApplication);
}

