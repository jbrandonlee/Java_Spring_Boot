package LAPService;

import java.util.List;

import LAPModels.leaveApplication;

public interface leaveService {
	  List<leaveApplication> findAllLeaveApplication();
	  List<leaveApplication> findLeaveApplicationByEID(int EmployeeId); 
	  public boolean saveEditApplication(leaveApplication application);
	  leaveApplication findLeaveApplication(Integer EmployeeId);
	  leaveApplication changeLeaveApplication(leaveApplication lvApp);
	  void removeCourse(leaveApplication lvApp);
}
