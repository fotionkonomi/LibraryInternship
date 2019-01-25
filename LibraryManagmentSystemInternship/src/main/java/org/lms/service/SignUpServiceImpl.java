package org.lms.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.lms.dao.SignUpDAO;
import org.lms.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private SignUpDAO signUpDAO;

	@Override
	@Transactional
	public void addUser(UserDTO userDTO) throws ConstraintViolationException {
		signUpDAO.addUser(userDTO);
	}

	public SignUpDAO getSignUpDAO() {
		return signUpDAO;
	}

	public void setSignUpDAO(SignUpDAO signUpDAO) {
		this.signUpDAO = signUpDAO;
	}

	@Override
	@Transactional
	public void addRolesForUser(UserDTO userDTO, List<String> rolesString) {
		this.signUpDAO.addRolesForUser(userDTO, rolesString);
	}

}
