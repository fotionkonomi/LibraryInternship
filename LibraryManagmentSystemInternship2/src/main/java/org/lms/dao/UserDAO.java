package org.lms.dao;

import java.util.List;
import org.lms.dto.UserDTO;
import org.lms.model.User;
import org.springframework.dao.DataIntegrityViolationException;

public interface UserDAO {

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

	User getUserById(UserDTO userDTO);

	UserDTO findUser(String username, String password);
	
	Boolean isAUserAdmin(UserDTO userDTO);
	
	Boolean isAUserSecretary(UserDTO userDTO);
	
	
}
