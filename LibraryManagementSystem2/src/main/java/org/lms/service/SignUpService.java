package org.lms.service;

import org.lms.dto.UserDTO;

public interface SignUpService {

	/**
	 * Adds a user in the system
	 * @param userDTO
	 */
    void addUser(UserDTO userDTO);

}
