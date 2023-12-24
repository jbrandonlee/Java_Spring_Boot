package sg.edu.nus.iss.lms.service;

import java.util.List;

import sg.edu.nus.iss.lms.model.Role;

public interface RoleService {
	public List<Role> findAllRoles();
	public Role findRoleById(String id);
}
