package org.lms.service;

import java.util.List;

import org.lms.dto.RoleDTO;
import org.lms.dto.UserDTO;
import org.lms.model.Role;

public interface RoleService {
	
	Role getRoleById(RoleDTO roleDTO);
	
	Role getAdminRole();
	
	Role getStudentRole();
	
	Role getSecretaryRole();
	
	List<RoleDTO> getRolesOfAUser(UserDTO userDTO);
}
