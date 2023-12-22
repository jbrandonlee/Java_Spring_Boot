package sg.edu.nus.iss.lms.validator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.edu.nus.iss.lms.model.Leave;
import sg.edu.nus.iss.lms.model.LeaveType;
import sg.edu.nus.iss.lms.service.HolidayService;
import sg.edu.nus.iss.lms.service.LeaveService;

@Component
public class LeaveValidator implements Validator {
	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private HolidayService holidayService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Leave.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Leave leave = (Leave) obj;
		LocalDate leaveStartDate = leave.getStartDate();
		LocalDate leaveEndDate = leave.getEndDate();		
		LeaveType leaveType = leave.getLeaveType();
		// This throws errors, cant get Employee as it is not binded on creation
		/*
		List<LeaveEntitlement> employeeLeaveEntitlements = leave.getEmployee().getLeaveEntitlements();
		double leaveDeductibleDays = leaveService.calculateDeductibleDaysInLeave(leave);
		double leaveBalance = getLeaveBalanceByType(employeeLeaveEntitlements, leaveType);
		*/
		
		// Require Contact if Destination is Overseas
		if (leave.getDestination().equalsIgnoreCase("Overseas")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contact",
					"error.contact", "Contact details are required for overseas leave.");
		}
		
		// Require End Date after Start Date
		if (leaveEndDate.isBefore(leaveStartDate)) {
			errors.rejectValue("endDate", "error.endDate","End Date must be after Start Date.");
		}
		
		// Require Start Date && End Date on Working Day
		if(!isWorkingDay(leaveStartDate)) {
			errors.rejectValue("startDate","error.startDate","Start Date must be on a working day.");
		}
		
		if(!isWorkingDay(leaveEndDate)) {
			errors.rejectValue("endDate","error.endDate","End Date must be on a working day.");
		}
		
		// Require Sufficient Leave Balance
		// Also handles Medical Leaves in Calendar Year <= 60 days
		/*
		if (leaveDeductibleDays > leaveBalance) {
			errors.rejectValue("endDate", "error.endDate", "Applied leave duration exceeds your remaining leave balance.");
		}
		*/
	}
	
	/*
	public double getLeaveBalanceByType(List<LeaveEntitlement> leaveEntitlements, LeaveType leaveType) {
		for (LeaveEntitlement le : leaveEntitlements) {
			if (le.getLeaveType().getType() == leaveType.getType())
				return le.getLeaveBalance();
		}
		return 0.0;
	}
	*/
	
	public boolean isWorkingDay(LocalDate date) {
		return !isWeekend(date) && !isHoliday(date);
	}
	
	public boolean isWeekend(LocalDate date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
	}
	
	public boolean isHoliday(LocalDate date) {
		List<LocalDate> publicHolidays = holidayService.findAllActiveHolidayDates();
		return publicHolidays.contains(date);
	}
	
}
