package sg.edu.nus.iss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.iss.lms.model.Role;

public interface RoleRepository extends JpaRepository<Role, String>{
	
}
