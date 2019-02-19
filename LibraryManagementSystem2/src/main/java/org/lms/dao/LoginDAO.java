package org.lms.dao;

import org.lms.dto.UserDTO;

public interface LoginDAO {

	/**
	 * Returns a user if both username and password match
	 * @param username
	 * @param password
	 * @return
	 */
	UserDTO login(String username, String password);
	
}
