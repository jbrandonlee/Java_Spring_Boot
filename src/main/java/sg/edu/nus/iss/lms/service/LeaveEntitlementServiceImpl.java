package sg.edu.nus.iss.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.LeaveEntitlement;
import sg.edu.nus.iss.lms.repository.LeaveEntitlementRepository;

@Service
@Transactional(readOnly = false)
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
	
	@Override
	@Transactional(readOnly = false)
	public LeaveEntitlement updateLeaveEntitlementBalanceByDays(Employee employee, String leaveTypeString, double days) {
		LeaveEntitlement leaveEnt = findLeaveEntitlementByEmployeeAndType(employee, leaveTypeString);
		leaveEnt.setLeaveBalance(leaveEnt.getLeaveBalance() + days);
		return leaveEntitlementRepo.saveAndFlush(leaveEnt);
	}

	@Override
	public List<LeaveEntitlement> findAll() {
		return leaveEntitlementRepo.findAll();
	}

	@Override
	public void save(LeaveEntitlement leaveEntitlement) {
		leaveEntitlementRepo.save(leaveEntitlement);
		
	}

	@Override
	public Optional<LeaveEntitlement> findById(Integer id) {
		return leaveEntitlementRepo.findById(id);
	}

	@Override
	public List<LeaveEntitlement> findByEmployeeId(Integer employeeId) {

		List<LeaveEntitlement> leaveEntitlementsList = leaveEntitlementRepo.findAllLeaveEntitlementByEmployeeId(employeeId);

		return leaveEntitlementsList;
	}
}
