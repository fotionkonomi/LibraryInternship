package org.lms.service;

import org.lms.dto.UserDTO;

public interface LoginService {

	/**
	 * Returns a user if the username and password match an existing user in the
	 * database
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	UserDTO login(String username, String password);

}
