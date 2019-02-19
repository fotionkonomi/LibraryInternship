package org.lms.dao;

import java.util.List;

import org.lms.dto.RoleDTO;
import org.lms.dto.UserDTO;
import org.lms.model.Role;

public interface RoleDAO {

	/**
	 * Returns a role model by its dto representation
	 * @param roleDTO
	 * @return
	 */
	Role getRoleById(RoleDTO roleDTO);
	
	/**
	 * Returns admin role
	 * @return
	 */
	Role getAdminRole();
	
	/**
	 * Returns student role
	 * @return
	 */
	Role getStudentRole();
	
	/**
	 * Returns secretary role
	 * @return
	 */
	Role getSecretaryRole();
	
	/**
	 * Returns a list of model objects by a list of their names
	 * @param rolesString
	 * @return
	 */
    List<Role> toModelListFromString(List<String> rolesString);
    
    /**
     * Returns the roles of a user
     * @param userDTO
     * @return
     */
    List<RoleDTO> rolesOfAUser(UserDTO userDTO);
    
    /**
     * Returns all roles
     * @return
     */
    List<RoleDTO> getAllRoles();
}
