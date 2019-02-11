package org.lms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lms.converter.UserConverter;
import org.lms.dto.UserDTO;
import org.lms.model.Role;
import org.lms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private RoleDAO roleDAO;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public List<UserDTO> listUser() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = session.createQuery("Select u From User u where u.activated=1").list();
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		for (User user : userList) {
			userDTOList.add(UserConverter.toDTO(user));
		}
		return userDTOList;
	}

	@Override
	public List<UserDTO> findUsersThatAreNotActivated() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select u from User u where u.activated=0");
		List<User> users = query.list();
		List<UserDTO> usersDTO = new ArrayList<>();
		for (User user : users) {
			usersDTO.add(UserConverter.toDTO(user));
		}
		return usersDTO;
	}

	@Override
	public void activateUser(UserDTO userDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = UserConverter.toModel(userDTO);
		user.setActivated(1);
		session.merge(user);
	}

	@Override
	public void deleteUser(UserDTO userDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = UserConverter.toModel(userDTO);
		session.delete(user);
	}

	@Override
	public void deActivateUser(UserDTO userDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = UserConverter.toModel(userDTO);
		user.setActivated(-1);
		session.merge(user);
	}

	@Override
	public UserDTO findUser(String username, String password) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("Select u from User u where u.username LIKE :username AND u.password LIKE :password");
		query.setString("username", username);
		query.setString("password", password);
		User user = (User) query.uniqueResult();
		if (user != null) {
			return UserConverter.toDTO(user);
		}
		return null;
	}

	@Override
	public User getUserById(UserDTO userDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select u from User u where u.userId=:userId");
		query.setParameter("userId", userDTO.getUserId());
		return (User) query.uniqueResult();
	}

	@Override
	public void updateUser(UserDTO userDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = getUserById(userDTO);
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAge(userDTO.getAge());
		session.merge(user);
	}

	@Override
	public void deleteAccount(UserDTO userDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		User userToDelete = getUserById(userDTO);
		userToDelete.setActivated(-2);
		session.merge(userToDelete);
	}

	@Override
	public void makeUserAdmin(UserDTO userDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = getUserById(userDTO);
		Role role = roleDAO.getAdminRole();
		user.addRole(role);
		session.merge(user);

	}

	@Override
	public Boolean isAUserAdmin(UserDTO userDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select r from Role r where r.roleId=1");
		Role role = (Role) query.uniqueResult();
		User user = getUserById(userDTO);
		if (user.getRolesOfThisUser().contains(role)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Boolean isAUserSecretary(UserDTO userDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select r from Role r where r.roleId=3");
		Role role = (Role) query.uniqueResult();
		User user = getUserById(userDTO);
		if (user.getRolesOfThisUser().contains(role)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void makeUserSecretary(UserDTO userDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = getUserById(userDTO);
		Role role = roleDAO.getSecretaryRole();
		user.addRole(role);
		session.merge(user);
	}

	@Override
	public void makeUserStudent(UserDTO userDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		UserDTO userDTOFound = findUser(userDTO.getUsername(), userDTO.getPassword());
		User user = getUserById(userDTOFound);
		Role role = roleDAO.getStudentRole();
		user.addRole(role);
		session.merge(user);
	}

}
