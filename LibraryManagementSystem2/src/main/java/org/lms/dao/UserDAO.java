package org.lms.dao;

import java.util.List;
import org.lms.dto.UserDTO;
import org.lms.model.User;

public interface UserDAO {

	/**
	 * Returns a list of all users that are active
	 * @return
	 */
	List<UserDTO> listUser();

	/**
	 * Returns a list of all users that are not activated yet
	 * @return
	 */
	List<UserDTO> findUsersThatAreNotActivated();

	/**
	 * Activates a user
	 * @param userDTO
	 */
	void activateUser(UserDTO userDTO);

	/**
	 * Deletes a user from the database
	 * @param userDTO
	 */
	void deleteUser(UserDTO userDTO);

	/**
	 * Disapproves a user's access to the system
	 * @param userDTO
	 */
	void deActivateUser(UserDTO userDTO);
		
	/**
	 * Updates the profile of a user
	 * @param userDTO
	 */
	void updateUser(UserDTO userDTO);
	
	/**
	 * Deactivates the profile of a user
	 * @param userDTO
	 */
	void deleteAccount(UserDTO userDTO);
	
	/**
	 * Makes the user admin
	 * @param userDTO
	 */
	void makeUserAdmin(UserDTO userDTO);
	
	/**
	 * Makes the user a secretary
	 * @param userDTO
	 */
	void makeUserSecretary(UserDTO userDTO);
	
	/**
	 * Makes a user a student
	 * @param userDTO
	 */
	void makeUserStudent(UserDTO userDTO);

	/**
	 * Returns a user by his id
	 * @param id
	 * @return
	 */
	User getUserById(int id);

	/**
	 * Finds a user by his username or password. If both of them 
	 * @param username
	 * @param password
	 * @return
	 */
	UserDTO findUser(String username, String password);
	
	/**
	 * Checks if a user is an admin
	 * @param userDTO
	 * @return
	 */
	Boolean isAUserAdmin(UserDTO userDTO);
	
	/**
	 * Checks if a user is a secretary
	 * @param userDTO
	 * @return
	 */
	Boolean isAUserSecretary(UserDTO userDTO);
	
	
}
