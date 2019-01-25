package org.lms.converter;

import java.util.ArrayList;
import java.util.List;

import org.lms.dto.RoleDTO;
import org.lms.dto.UserDTO;
import org.lms.model.Role;
import org.lms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserConverterImpl implements UserConverter {

	@Autowired
	private RoleConverter roleConverter;

	public User toModel(UserDTO userDTO) {
		User user = new User();
		user.setUserId(userDTO.getUserId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setUsername(userDTO.getUsername());
		user.setAge(userDTO.getAge());
		user.setActivated(userDTO.getActivated());
		user.setEmail(userDTO.getEmail());
		user.setGender(userDTO.getGender());
		user.setPassword(userDTO.getPassword());
	/*	List<RoleDTO> rolesDTO = userDTO.getRolesOfThisUser();
		List<Role> roles = new ArrayList<>();
		for (RoleDTO roleDTO : rolesDTO) {
			roles.add(roleConverter.toModel(roleDTO));
		}
		user.setRolesOfThisUser(roles);*/
		return user;

	}

	public UserDTO toDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(user.getUserId());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setUsername(user.getUsername());
		userDTO.setAge(user.getAge());
		userDTO.setActivated(user.getActivated());
		userDTO.setEmail(user.getEmail());
		userDTO.setGender(user.getGender());
		userDTO.setPassword(user.getPassword());
	/*	List<Role> roles = user.getRolesOfThisUser();
		List<RoleDTO> rolesDTO = new ArrayList<>();
		for (Role role : roles) {
			rolesDTO.add(roleConverter.toDTO(role));
		}
		userDTO.setRolesOfThisUser(rolesDTO);
*/
		return userDTO;

	}

	public RoleConverter getRoleConverter() {
		return roleConverter;
	}

	public void setRoleConverter(RoleConverter roleConverter) {
		this.roleConverter = roleConverter;
	}

}
