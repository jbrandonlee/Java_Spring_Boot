package sg.edu.nus.iss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.iss.lms.model.Overtime;

public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {

}
