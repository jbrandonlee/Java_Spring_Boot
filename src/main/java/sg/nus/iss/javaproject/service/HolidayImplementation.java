package sg.nus.iss.javaproject.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.javaproject.repository.HolidayRepository;

@Service
@Transactional
public class HolidayImplementation implements HolidayInterface{
	@Autowired
	HolidayRepository hRepo;

	@Override
	public List<LocalDate> getAllHoliday(){
		return hRepo.findAllHoliday();
	}
}
