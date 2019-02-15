package org.lms.dao;

import org.lms.dto.UserDTO;

public interface LoginDAO {

	UserDTO login(String username, String password);
	
}
