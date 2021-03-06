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
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;

	@Override
	public void addUser(UserDTO userDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = UserConverter.toModel(userDTO);
		session.persist(user);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addRolesForUser(UserDTO userDTO, List<String> rolesString) {
		Session session = this.sessionFactory.getCurrentSession();
		UserDTO userDTOFound = this.userDAO.findUser(userDTO.getUsername(), userDTO.getPassword());
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
