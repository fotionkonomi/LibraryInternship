package org.lms.service;

import org.lms.dao.SignUpDAO;
import org.lms.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private SignUpDAO signUpDAO;

	@Override
	public void addUser(UserDTO userDTO) {
		signUpDAO.addUser(userDTO);
	}

	public SignUpDAO getSignUpDAO() {
		return signUpDAO;
	}

	public void setSignUpDAO(SignUpDAO signUpDAO) {
		this.signUpDAO = signUpDAO;
	}


}
