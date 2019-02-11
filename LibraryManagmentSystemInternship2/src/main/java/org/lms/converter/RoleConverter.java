package org.lms.converter;

import org.lms.dto.RoleDTO;
import org.lms.dto.UserDTO;
import org.lms.model.Role;
import org.lms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public class RoleConverter {

	public static Role toModel(RoleDTO roleDTO) {
		Role role = new Role();
	//	role.setRoleId(roleDTO.getRoleId());
		role.setRoleName(roleDTO.getRoleName());
		role.setRoleDescription(roleDTO.getRoleDescription());
	/*	List<UserDTO> usersDTO = roleDTO.getUsersThatHaveThisRole();
		List<User> users = new ArrayList<User>();
		for(UserDTO userDTO : usersDTO) {
			users.add(userConverter.toModel(userDTO));
		}
		role.setUsersThatHaveThisRole(users);*/
		return role;
	}

	public static RoleDTO toDTO(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setRoleId(role.getRoleId());
		roleDTO.setRoleName(role.getRoleName());
		roleDTO.setRoleDescription(role.getRoleDescription());
	/*	List<User> users = role.getUsersThatHaveThisRole();
		List<UserDTO> usersDTO = new ArrayList<>();
		for(User user: users) {
			usersDTO.add(userConverter.toDTO(user));
		}
		roleDTO.setUsersThatHaveThisRole(usersDTO); */
		return roleDTO;
	}

}
