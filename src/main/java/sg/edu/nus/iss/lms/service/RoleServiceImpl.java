package sg.edu.nus.iss.lms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.model.Role;
import sg.edu.nus.iss.lms.repository.RoleRepository;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepo;
	
	@Override
	public List<Role> findAllRoles() {
		return roleRepo.findAll();
	}
	
	@Override
	public Role findRoleById(String id) {
		return roleRepo.findById(id).orElse(null);
	}
}
