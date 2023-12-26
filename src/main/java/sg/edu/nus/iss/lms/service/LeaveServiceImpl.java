package sg.edu.nus.iss.lms.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.model.Employee;
import sg.edu.nus.iss.lms.model.LeaveApplication;
import sg.edu.nus.iss.lms.model.LeaveApplication.DaySection;
import sg.edu.nus.iss.lms.model.LeaveApplication.LeaveStatus;
import sg.edu.nus.iss.lms.repository.HolidayRepository;
import sg.edu.nus.iss.lms.repository.LeaveRepository;

@Service
@Transactional(readOnly = true)
public class LeaveServiceImpl implements LeaveService {
	@Autowired
	LeaveRepository leaveRepo;
	
	@Autowired
	HolidayRepository holidayRepo;
	
	@Autowired
	LeaveEntitlementService leaveEntService;
	
	// -- Employee --	
	@Override
	@Transactional(readOnly = false)
	public LeaveApplication createLeave(LeaveApplication leave) {
		leaveEntService.updateLeaveEntitlementBalanceByDays(leave.getEmployee(), leave.getLeaveType().getType(), -calculateDeductibleDaysInLeave(leave));
		return leaveRepo.saveAndFlush(leave);
	}
	
	@Override
	@Transactional(readOnly = false)
	public LeaveApplication updateLeave(LeaveApplication leave) {
		double originalDeduction = calculateDeductibleDaysInLeave(findEmployeeLeaveId(leave.getEmployee(), leave.getId()));
		double currentDeduction = calculateDeductibleDaysInLeave(leave);
		
		if (leave.getLeaveStatus() == LeaveStatus.CANCELLED || leave.getLeaveStatus() == LeaveStatus.DELETED || leave.getLeaveStatus() == LeaveStatus.REJECTED) {
			currentDeduction = 0;
		}
		
		leaveEntService.updateLeaveEntitlementBalanceByDays(leave.getEmployee(), leave.getLeaveType().getType(), originalDeduction - currentDeduction);
		return leaveRepo.saveAndFlush(leave);
	}
	
	@Override
	public List<LeaveApplication> findAllEmployeeLeaves(Employee employee) {
		return leaveRepo.findAllLeaveByEmployeeId(employee.getId());
	}

	@Override
	public List<LeaveApplication> findEmployeeLeavesCurrYear(Employee employee) {
		return leaveRepo.findCurrYearLeaveByEmployeeId(employee.getId());
	}
	
	@Override
	public List<LeaveApplication> findEmployeeLeavesUpcoming(Employee employee) {
		return leaveRepo.findUpcomingLeaveByEmployeeId(employee.getId());
	}
	
	@Override
	public LeaveApplication findEmployeeLeaveId(Employee employee, Integer leaveId) {
		return leaveRepo.findEmployeeLeaveById(employee.getId(), leaveId);
	}
	
    
    // -- Manager --
    public List<LeaveApplication> findAllSubordinatePendingLeaves(Employee manager) {
		return leaveRepo.findAllSubordinatePendingLeaves(manager.getId());
	}
	
    public List<LeaveApplication> findOverlappingSubordinateLeaves(Employee manager, LeaveApplication leave) {
		return leaveRepo.findOverlappingSubordinateLeaves(manager.getId(), leave.getId(), leave.getStartDate(), leave.getEndDate());
	}
	
    public List<LeaveApplication> findSubordinateLeaveHistory(Employee manager, Integer subordinateId) {
		return leaveRepo.findSubordinateLeaveHistory(manager.getId(), subordinateId);
	}
	
    public LeaveApplication findSubordinateLeaveById(Employee manager, Integer subordinateId, Integer leaveId) {
		return leaveRepo.findSubordinateLeaveById(manager.getId(), subordinateId, leaveId);
	}
    
    // -- Utility --
    @Override
    public Page<LeaveApplication> getPaginatedLeaves(int page, int pageSize, List<LeaveApplication> listLeaves) {
        Pageable pageRequest = PageRequest.of(page, pageSize);

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), listLeaves.size());

        List<LeaveApplication> pageContent = listLeaves.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, listLeaves.size());
    }
    
	@Override
	public double calculateDeductibleDaysInLeave(LeaveApplication leave) {
		// If Leave Period > 14 days, weekends and public holidays are included
		if (leave.getCalendarDuration() > 14) {
			return leave.getCalendarDuration();
		}
		
		// Else, weekends and public holidays are excluded
		LocalDate startDate = leave.getStartDate();
		LocalDate endDate = leave.getEndDate();
		DaySection startDaySection = leave.getStartDaySection();
		DaySection endDaySection = leave.getEndDaySection();
		Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
		List<LocalDate> holidayDates = holidayRepo.findAllActiveHolidayDates();
		
		if (endDate.isBefore(startDate)) {
			return 0.0;
		}
		
		long fullDays = startDate.datesUntil(endDate)
		        .filter(d -> !weekend.contains(d.getDayOfWeek()) && !holidayDates.contains(d))
		        .count();
		
		// Assumes Leave startDate and endDate are working days (taken care of by LeaveValidator)
		double halfDay = 0.0;
		
		if (startDaySection == DaySection.AM && endDaySection == DaySection.PM) {
			halfDay = 1.0;
		} else if (startDaySection == DaySection.PM && endDaySection == DaySection.AM) {
			halfDay = 0.0;
		} else if (startDaySection == endDaySection) {
			halfDay = 0.5;
		}
		
		return fullDays + halfDay;
	}
}
