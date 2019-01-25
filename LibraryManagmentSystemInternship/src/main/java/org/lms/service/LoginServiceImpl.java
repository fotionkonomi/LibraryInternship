package org.lms.service;

import org.lms.dao.LoginDAO;
import org.lms.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO loginDAO;

	
	
	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	@Override
	@Transactional
	public UserDTO login(String username, String password) {
		return this.loginDAO.login(username, password);
	}
	
	
	
}
