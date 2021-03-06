package org.lms.dao;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.lms.dto.UserDTO;

public interface SignUpDAO {

	void addUser(UserDTO userDTO) throws ConstraintViolationException;

	void addRolesForUser(UserDTO userDTO, List<String> rolesString);

}
