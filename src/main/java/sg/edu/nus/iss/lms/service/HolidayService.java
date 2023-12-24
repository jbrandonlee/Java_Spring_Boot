package sg.edu.nus.iss.lms.service;

import java.time.LocalDate;
import java.util.List;

import sg.edu.nus.iss.lms.model.Holiday;

public interface HolidayService {
	public Holiday createHoliday(Holiday holiday);
	public Holiday updateHoliday(Holiday holiday);
	public List<Holiday> findAllHolidays();
	public Holiday findHolidayById(Integer id);
	public List<Holiday> findAllActiveHolidays();
	public List<LocalDate> findAllActiveHolidayDates();
}
