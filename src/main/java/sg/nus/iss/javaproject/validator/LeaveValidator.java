package sg.nus.iss.javaproject.validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.nus.iss.javaproject.model.LeaveApplication;
import sg.nus.iss.javaproject.model.LeaveType;

@Component
public class LeaveValidator implements Validator{
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
					if(leaveType==LeaveType.medical) {
						
						if(days>60) {
							errors.rejectValue("leaveEndDate","error.leaveEndDate","medical leave is limitted to 60 days");
						}
					}
					
					if(leaveType==LeaveType.annual) {
						if(days>leaveDays) {
							errors.rejectValue("leaveEndDate","error.leaveEndDate","Sorry,you can only have "+leaveDays+" days leave");
						}
					}
			}
		}
		
		//Configure the annualdays

	}
}
//LeaveType leaveType=leaveApplication.getLeaveType();

		//if(leaveType!=null&&!EnumSet.of(LeaveType.annual, LeaveType.compensation, LeaveType.medical).contains(leaveType)) {
		//	errors.rejectValue("leaveType","error.leaveType","leaveType can only be annual,compensation or medical");
		//}