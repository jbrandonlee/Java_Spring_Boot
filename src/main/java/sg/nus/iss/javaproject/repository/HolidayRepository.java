package sg.nus.iss.javaproject.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.nus.iss.javaproject.model.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday,Integer>{
	@Query("SELECT h.day FROM Holiday h")
	public List<LocalDate> findAllHoliday();
}
