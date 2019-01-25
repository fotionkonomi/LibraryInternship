package org.lms.service;

import java.util.List;

import org.lms.dao.RoleDAO;
import org.lms.dto.RoleDTO;
import org.lms.dto.UserDTO;
import org.lms.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	@Transactional
	public Role getRoleById(RoleDTO roleDTO) {
		return this.roleDAO.getRoleById(roleDTO);
	}

	@Override
	public Role getAdminRole() {
		return this.roleDAO.getAdminRole();
	}

	@Override
	public Role getStudentRole() {
		return this.roleDAO.getStudentRole();
	}

	@Override
	public Role getSecretaryRole() {
		return this.roleDAO.getSecretaryRole();
	}

	@Override
	@Transactional
	public List<RoleDTO> getRolesOfAUser(UserDTO userDTO) {
		return roleDAO.rolesOfAUser(userDTO);
	}

	

	
	

		
}
