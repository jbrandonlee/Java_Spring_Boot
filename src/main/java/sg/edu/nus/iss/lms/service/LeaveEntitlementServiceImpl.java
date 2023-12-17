package sg.edu.nus.iss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.LeaveEntitlement;
import sg.edu.nus.iss.lms.repository.LeaveEntitlementRepository;

@Service
@Transactional(readOnly = true)
public class LeaveEntitlementServiceImpl implements LeaveEntitlementService {
	@Autowired
	LeaveEntitlementRepository leaveEntitlementRepo;
	
	@Override
	public List<LeaveEntitlement> findAllLeaveEntitlementByEmployee(Employee employee) {
		return leaveEntitlementRepo.findAllLeaveEntitlementByEmployeeId(employee.getId());
	};
	
	@Override
	public LeaveEntitlement findLeaveEntitlementByEmployeeAndType(Employee employee, String leaveTypeString) {
		return leaveEntitlementRepo.findLeaveEntitlementByEmployeeIdAndType(employee.getId(), leaveTypeString);
	};
}
