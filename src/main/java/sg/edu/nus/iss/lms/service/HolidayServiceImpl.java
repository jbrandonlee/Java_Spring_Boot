package sg.edu.nus.iss.lms.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.model.Holiday;
import sg.edu.nus.iss.lms.repository.HolidayRepository;

@Service
@Transactional(readOnly = true)
public class HolidayServiceImpl implements HolidayService {
	@Autowired
	HolidayRepository holidayRepo;
	
	@Override
	public Holiday createHoliday(Holiday holiday) {
		return holidayRepo.saveAndFlush(holiday);
	}
	
	@Override
	public Holiday updateHoliday(Holiday holiday) {
		return holidayRepo.saveAndFlush(holiday);
	}
	
	@Override
	public List<Holiday> findAllHolidays() {
		return holidayRepo.findAll();
	}
	
	@Override
	public Holiday findHolidayById(Integer id) {
		return holidayRepo.findById(id).orElse(null);
	}
	
	@Override
	public List<Holiday> findAllActiveHolidays() {
		return holidayRepo.findAllActiveHolidays();
	}
	
	@Override
	public List<LocalDate> findAllActiveHolidayDates() {
		return holidayRepo.findAllActiveHolidayDates();
	}

}
