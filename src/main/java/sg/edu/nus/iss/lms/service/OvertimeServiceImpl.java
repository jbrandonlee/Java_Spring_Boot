package sg.edu.nus.iss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.Overtime;
import sg.edu.nus.iss.lms.repository.OvertimeRepository;

@Service
@Transactional(readOnly = true)
public class OvertimeServiceImpl implements OvertimeService {
	@Autowired
	OvertimeRepository overtimeRepo;
	
	public List<Overtime> findAllEmployeeOvertime(Employee employee) {
		return overtimeRepo.findAllOvertimeByEmployeeId(employee.getId());
	}
	
	public Overtime findEmployeeOvertimeId(Employee employee, Integer leaveId) {
		return overtimeRepo.findEmployeeOvertimeById(employee.getId(), leaveId);
	}
	
	public List<Overtime> findAllSubordinatePendingOvertimes(Employee manager) {
		return overtimeRepo.findAllSubordinatePendingOvertimes(manager.getId());
	}
}
