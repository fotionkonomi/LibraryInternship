package org.lms.service;

import java.util.List;

import org.lms.dto.RoleDTO;
import org.lms.dto.UserDTO;
import org.lms.model.Role;

public interface RoleService {

	/**
	 * Returns a role model by its dto representation
	 * 
	 * @param roleDTO
	 * @return
	 */
	Role getRoleById(RoleDTO roleDTO);

	/**
	 * Returns the admin role
	 * 
	 * @return
	 */
	Role getAdminRole();

	/**
	 * Returns the student role
	 * 
	 * @return
	 */
	Role getStudentRole();

	/**
	 * Returns the secretary role
	 * 
	 * @return
	 */
	Role getSecretaryRole();

	/**
	 * Returns the roles of a user
	 * 
	 * @param userDTO
	 * @return
	 */
	List<RoleDTO> getRolesOfAUser(UserDTO userDTO);

	/**
	 * Returns all roles
	 * 
	 * @return
	 */
	List<RoleDTO> getAllRoles();

	/**
	 * Returns all role names
	 * 
	 * @return
	 */
	List<String> getAllRolesAsString();
}
