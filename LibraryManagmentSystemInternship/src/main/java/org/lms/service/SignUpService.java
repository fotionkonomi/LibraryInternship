package org.lms.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.lms.dto.UserDTO;

public interface SignUpService {

    void addUser(UserDTO userDTO) throws ConstraintViolationException;

	void addRolesForUser(UserDTO userDTO, List<String> rolesString);

}
