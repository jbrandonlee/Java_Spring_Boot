package sg.edu.nus.iss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.iss.lms.model.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Integer>{

}
