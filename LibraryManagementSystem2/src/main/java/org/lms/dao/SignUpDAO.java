package org.lms.dao;

import org.lms.dto.UserDTO;

public interface SignUpDAO {

	/**
	 * Adds a user in the database
	 * @param userDTO
	 */
	void addUser(UserDTO userDTO);

}
