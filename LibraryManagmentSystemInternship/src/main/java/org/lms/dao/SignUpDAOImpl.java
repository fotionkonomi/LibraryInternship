package org.lms.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.lms.converter.UserConverter;
import org.lms.dto.UserDTO;
import org.lms.model.Role;
import org.lms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SignUpDAOImpl implements SignUpDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public void addUser(UserDTO userDTO) throws ConstraintViolationException {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(userConverter.toModel(userDTO));
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public UserConverter getUserConverter() {
		return userConverter;
	}

	public void setUserConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

	@Override
	public void addRolesForUser(UserDTO userDTO, List<String> rolesString) {
		Session session = this.sessionFactory.getCurrentSession();
		UserDTO userDTOFound = this.userDAO.findUser(userDTO.getUsername(), userDTO.getPassword());
		System.out.println("NULL APO JO " + userDTO);
		User user = this.userDAO.getUserById(userDTOFound);
		List<Role> roles = roleDAO.toModelListFromString(rolesString);
		for (Role role : roles) {
			user.addRole(role);
		}
		session.merge(user);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

}
