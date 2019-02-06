package org.lms.converter;

import org.lms.dto.RoleDTO;
import org.lms.model.Role;

public interface RoleConverter {

	RoleDTO toDTO(Role role);
	Role toModel(RoleDTO roleDTO);
}
