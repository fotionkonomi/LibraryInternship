package org.lms.converter;

import org.lms.dto.UserDTO;
import org.lms.model.User;

public interface UserConverter {

	UserDTO toDTO(User user);

	User toModel(UserDTO userDTO);
}
