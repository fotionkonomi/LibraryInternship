package org.lms.service;

import java.util.List;

import org.lms.dto.UserDTO;

public interface UserService {

	List<UserDTO> listUser();

	List<UserDTO> findUsersThatAreNotActivated();

	void activateUser(UserDTO userDTO);

	void deleteUser(UserDTO userDTO);

	void deActivateUser(UserDTO userDTO);

	void updateUser(UserDTO userDTO);

	void deleteAccount(UserDTO userDTO);

	void makeUserAdmin(UserDTO userDTO);

	void makeUserSecretary(UserDTO userDTO);

	void makeUserStudent(UserDTO userDTO);

	Boolean isAUserAdmin(UserDTO userDTO);

	Boolean isAUserSecretary(UserDTO userDTO);
	
	UserDTO getUserById(int id);
}
