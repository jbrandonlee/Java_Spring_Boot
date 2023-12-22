package sg.edu.nus.iss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.iss.lms.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}