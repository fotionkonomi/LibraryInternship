package org.lms.service;

import java.util.List;

import org.lms.dto.UserDTO;

public interface UserService {

	/**
	 * Returns a list of all users in the system that are active
	 * 
	 * @return
	 */
	List<UserDTO> listUser();

	/**
	 * Returns a list of all users in the database that aren't activated yet
	 * 
	 * @return
	 */
	List<UserDTO> findUsersThatAreNotActivated();

	/**
	 * Activates the user's profile
	 * 
	 * @param userDTO
	 */
	void activateUser(UserDTO userDTO);

	/**
	 * Deletes a user from the system
	 * 
	 * @param userDTO
	 */
	void deleteUser(UserDTO userDTO);

	/**
	 * Disapproves the access of a user in the system
	 * 
	 * @param userDTO
	 */
	void deActivateUser(UserDTO userDTO);

	/**
	 * Updates a user's profile with new data
	 * 
	 * @param userDTO
	 */
	void updateUser(UserDTO userDTO);

	/**
	 * Deactivates the profile of a user
	 * 
	 * @param userDTO
	 */
	void deleteAccount(UserDTO userDTO);

	/**
	 * Makes the user an admin
	 * 
	 * @param userDTO
	 */
	void makeUserAdmin(UserDTO userDTO);

	/**
	 * Makes the user a secretary
	 * 
	 * @param userDTO
	 */
	void makeUserSecretary(UserDTO userDTO);

	/**
	 * Makes the user a student
	 * 
	 * @param userDTO
	 */
	void makeUserStudent(UserDTO userDTO);

	/**
	 * Checks if a user is an admin
	 * 
	 * @param userDTO
	 * @return
	 */
	Boolean isAUserAdmin(UserDTO userDTO);

	/**
	 * Checks if a user is a secretary
	 * 
	 * @param userDTO
	 * @return
	 */
	Boolean isAUserSecretary(UserDTO userDTO);

	/**
	 * Searches a user by his id and returns him
	 * 
	 * @param id
	 * @return
	 */
	UserDTO getUserById(int id);

	/**
	 * Sets the roles defined in the list to the user
	 * 
	 * @param rolesString
	 * @param userDTO
	 */
	void setRolesToUserViaListString(List<String> rolesString, UserDTO userDTO);
}
