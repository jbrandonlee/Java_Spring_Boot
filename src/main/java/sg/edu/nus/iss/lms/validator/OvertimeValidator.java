package sg.edu.nus.iss.lms.validator;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.edu.nus.iss.lms.model.OvertimeClaim;

@Component
public class OvertimeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return OvertimeClaim.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		OvertimeClaim overtime = (OvertimeClaim) obj;
		LocalDateTime startTime = overtime.getStartTime();
		LocalDateTime endTime = overtime.getEndTime();
		
		// Require End Time after Start Time
		if (endTime.isBefore(startTime)) {
			errors.rejectValue("endTime", "error.endTime", "End Time must be after Start Time.");
		}
		
		// Require minimum 4 hours of Overtime Work
		if (Duration.between(startTime, endTime).toHours() < 4) {
			errors.rejectValue("endTime", "error.endTime", "Minimum 4hrs of overtime work required for claim.");
		}
	}
}
