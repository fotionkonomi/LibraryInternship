package org.lms.service;

import java.util.List;

import org.lms.converter.UserConverter;
import org.lms.dao.UserDAO;
import org.lms.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public List<UserDTO> listUser() {
		return userDAO.listUser();
	}

	@Override
	public List<UserDTO> findUsersThatAreNotActivated() {
		return userDAO.findUsersThatAreNotActivated();
	}

	@Override
	public void activateUser(UserDTO userDTO) {
		this.userDAO.activateUser(userDTO);
	}

	@Override
	public void deleteUser(UserDTO userDTO) {
		this.userDAO.deleteUser(userDTO);
	}

	@Override

	public void deActivateUser(UserDTO userDTO) {
		this.userDAO.deActivateUser(userDTO);
	}

	@Override

	public void updateUser(UserDTO userDTO) {
		this.userDAO.updateUser(userDTO);
	}

	@Override
	public void deleteAccount(UserDTO userDTO) {
		this.userDAO.deleteAccount(userDTO);
	}

	@Override
	public void makeUserAdmin(UserDTO userDTO) {
		this.userDAO.makeUserAdmin(userDTO);
	}

	@Override
	public void makeUserSecretary(UserDTO userDTO) {
		this.userDAO.makeUserSecretary(userDTO);
	}

	@Override
	public void makeUserStudent(UserDTO userDTO) {
		this.userDAO.makeUserStudent(userDTO);
	}

	@Override
	public Boolean isAUserAdmin(UserDTO userDTO) {
		return this.userDAO.isAUserAdmin(userDTO);
	}

	@Override
	public Boolean isAUserSecretary(UserDTO userDTO) {
		return this.userDAO.isAUserSecretary(userDTO);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserDTO getUserById(int id) {
		return UserConverter.toDTO(userDAO.getUserById(id));
	}

}
