package org.lms.converter;

import org.lms.dto.RoleDTO;
import org.lms.model.Role;

public class RoleConverter {

	public static Role toModel(RoleDTO roleDTO) {
		Role role = new Role();
		role.setRoleId(roleDTO.getRoleId());
		role.setRoleName(roleDTO.getRoleName());
		role.setRoleDescription(roleDTO.getRoleDescription());
	
		return role;
	}

	public static RoleDTO toDTO(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setRoleId(role.getRoleId());
		roleDTO.setRoleName(role.getRoleName());
		roleDTO.setRoleDescription(role.getRoleDescription());
	
		return roleDTO;
	}

}
