package org.lms.service;

import org.lms.dto.UserDTO;

public interface LoginService {

	UserDTO login(String username, String password);

}
