package sg.nus.iss.javaproject.validator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.iss.javaproject.model.LeaveApplication;
import sg.nus.iss.javaproject.model.LeaveType;
import sg.nus.iss.javaproject.service.HolidayImplementation;
import sg.nus.iss.javaproject.service.HolidayInterface;

@Component
public class LeaveValidator implements Validator{
	@Autowired
	private HolidayInterface hService;
	
	@Autowired
	public void setHolidayService(HolidayImplementation hImpl) {
		this.hService=hImpl;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LeaveApplication.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object obj,Errors errors) {
		LeaveApplication leaveApplication=(LeaveApplication)obj;
		
		//Configure the destination
		String destination=leaveApplication.getDestination();
		String leavePhoneNumber=leaveApplication.getLeavePhoneNumber();
		if(!destination.equalsIgnoreCase("Singapore")) {
		ValidationUtils.rejectIfEmpty(errors, "leavePhoneNumber",
				"error.leavePhoneNumber", "Phone Number is required.");
		}
		
		//Configure the date
		LocalDate leaveStartDate=leaveApplication.getLeaveStartDate();
		LocalDate leaveEndDate=leaveApplication.getLeaveEndDate();		
		LeaveType leaveType=leaveApplication.getLeaveType();
		long leaveDays=leaveApplication.getLeaveDays();
		
		if(leaveEndDate!=null&&leaveStartDate!=null) {
			if(leaveEndDate.isBefore(leaveStartDate)) {
				errors.rejectValue("leaveEndDate", "error.leaveEndDate","leaveEndDate can not be earlier than leaveStartDate");
			}
			else if(leaveType!=null) {
				long days=ChronoUnit.DAYS.between(leaveStartDate,leaveEndDate);
				
				//Configure the medical
				if(leaveType==LeaveType.medical) {
					if(days>60) {
						errors.rejectValue("leaveEndDate","error.leaveEndDate","medical leave is limitted to 60 days");						
						}
					}	
				
				//Configure the annualdays
				if(leaveType==LeaveType.annual) {
					if(!isWeekDay(leaveEndDate)) {
						errors.rejectValue("leaveEndDate","error.leaveEndDate","leaveEndDate must be working days");
					}
					if(!isWeekDay(leaveStartDate)) {
						errors.rejectValue("leaveStartDate","error.leaveStartDate","leaveStartDate must be working days");
					}
					else{
						if(leaveDays<=14) {
							days-=daysInclude(leaveEndDate,leaveStartDate);
//							System.out.print(leaveDays);
//							System.out.print(days);
						}
						if(days>leaveDays) {
							errors.rejectValue("leaveEndDate","error.leaveEndDate","Sorry,you can only have "+leaveDays+" days leave");
						}
					}
				}
			}
		}
	}
	
	private boolean isWeekDay(LocalDate time) {
		DayOfWeek dayOfWeek = time.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
	}
	
	private long daysInclude(LocalDate endDate,LocalDate startDate) {
		LocalDate currentDate = startDate;
		long days=0;
        while (!currentDate.isAfter(endDate)) {
            if (!isWeekDay(currentDate) || isPublicHoliday(currentDate)) {
            	days=days+1;
            }
            currentDate = currentDate.plusDays(1);
        }
        return days;
	}
	
	private boolean isPublicHoliday(LocalDate time) {
		List<LocalDate> publicHolidays = hService.getAllHoliday();
		return publicHolidays.contains(time);
	}
}
//LeaveType leaveType=leaveApplication.getLeaveType();

		//if(leaveType!=null&&!EnumSet.of(LeaveType.annual, LeaveType.compensation, LeaveType.medical).contains(leaveType)) {
		//	errors.rejectValue("leaveType","error.leaveType","leaveType can only be annual,compensation or medical");
		//}