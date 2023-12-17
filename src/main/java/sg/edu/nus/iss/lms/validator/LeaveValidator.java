package sg.edu.nus.iss.lms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.edu.nus.iss.lms.model.LeaveApplication;


@Component
public class LeaveValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		return LeaveApplication.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object obj,Errors errors) {
		LeaveApplication leaveApplication=(LeaveApplication)obj;
		String destination=leaveApplication.getDestination();
		if(!destination.equalsIgnoreCase("Singapore")) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "leavePhoneNumber",
				"error.leavePhoneNumber", "Phone Number is required.");
		}
	}
}
