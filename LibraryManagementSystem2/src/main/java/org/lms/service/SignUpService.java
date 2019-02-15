package org.lms.service;

import java.util.List;

import org.lms.dto.UserDTO;

public interface SignUpService {

    void addUser(UserDTO userDTO);

	void addRolesForUser(UserDTO userDTO, List<String> rolesString);

}
