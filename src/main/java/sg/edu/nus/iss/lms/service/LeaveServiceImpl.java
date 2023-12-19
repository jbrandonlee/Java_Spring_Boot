package sg.edu.nus.iss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.Leave;
import sg.edu.nus.iss.lms.repository.LeaveRepository;

@Service
@Transactional(readOnly = true)
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	LeaveRepository leaveRepo;
	
	// -- Employee --
	@Override
	public Leave createLeave(Leave leave) {
		return leaveRepo.saveAndFlush(leave);
	}
	
	@Override
	public Leave updateLeave(Leave leave) {
		return leaveRepo.saveAndFlush(leave);
	}
	
	@Override
	public List<Leave> findAllEmployeeLeaves(Employee employee) {
		return leaveRepo.findAllLeaveByEmployeeId(employee.getId());
	}

	@Override
	public List<Leave> findEmployeeLeavesCurrYear(Employee employee) {
		return leaveRepo.findCurrYearLeaveByEmployeeId(employee.getId());
	}
	
	@Override
	public List<Leave> findEmployeeLeavesUpcoming(Employee employee) {
		return leaveRepo.findUpcomingLeaveByEmployeeId(employee.getId());
	}
	
	@Override
	public Leave findEmployeeLeaveId(Employee employee, Integer leaveId) {
		return leaveRepo.findEmployeeLeaveById(employee.getId(), leaveId);
	}
	
    @Override
	// https://www.baeldung.com/spring-data-jpa-convert-list-page
    // https://www.baeldung.com/spring-thymeleaf-pagination
	// Use another service to create input parameter listLeaves
    public Page<Leave> getPaginatedLeaves(int page, int pageSize, List<Leave> listLeaves) {
        Pageable pageRequest = PageRequest.of(page, pageSize);

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), listLeaves.size());

        List<Leave> pageContent = listLeaves.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, listLeaves.size());
    }
    
    // -- Manager --
    public List<Leave> findAllSubordinatePendingLeaves(Employee manager) {
		return leaveRepo.findAllSubordinatePendingLeaves(manager.getId());
	}
	
    public List<Leave> findSubordinateLeaveHistoryInDuration(Employee manager, Leave leave) {
		return leaveRepo.findSubordinateLeaveHistoryInDuration(manager.getId(), leave.getStartDate(), leave.getEndDate());
	}
	
    public List<Leave> findSubordinateApprovedLeaveHistory(Employee manager, Employee employee) {
		return leaveRepo.findSubordinateApprovedLeaveHistory(manager.getId(), employee.getId());
	}
	
    public List<Leave> findSubordinateLeaveById(Employee employee, Employee manager, Integer leaveId) {
		return leaveRepo.findSubordinateLeaveById(employee.getId(), manager.getId(), leaveId);
	}
}
