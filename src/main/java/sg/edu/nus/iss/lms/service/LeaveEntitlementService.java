package sg.edu.nus.iss.lms.service;

import java.util.List;

import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.LeaveEntitlement;

public interface LeaveEntitlementService {
	public LeaveEntitlement createLeaveEntitlement(LeaveEntitlement leaveEntitlement);
	public LeaveEntitlement updateLeaveEntitlement(LeaveEntitlement leaveEntitlement);	
	public List<LeaveEntitlement> findAllLeaveEnts();
	public LeaveEntitlement findLeaveEntById(Integer id);
	public List<LeaveEntitlement> findAllLeaveEntitlementByEmployee(Employee employee);
	public LeaveEntitlement findLeaveEntitlementByEmployeeAndType(Employee employee, String leaveTypeString);
	public LeaveEntitlement updateLeaveEntitlementBalanceByDays(Employee employee, String leaveTypeString, double days);
}
