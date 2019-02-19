package org.lms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lms.converter.RoleConverter;
import org.lms.dto.RoleDTO;
import org.lms.dto.UserDTO;
import org.lms.model.Role;
import org.lms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAOImpl implements RoleDAO {

	private SessionFactory sessionFactory;
	
	@Autowired
	private UserDAO userDAO;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public Role getRoleById(RoleDTO roleDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select r from Role r where r.roleId = :roleId");
		query.setParameter("roleId", roleDTO.getRoleId());
		return (Role) query.uniqueResult();
	}

	@Override
	public Role getAdminRole() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select r from Role r where r.roleId = 1");
		return (Role) query.uniqueResult();
	}

	@Override
	public Role getStudentRole() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select r from Role r where r.roleId = 2");
		return (Role) query.uniqueResult();
	}

	@Override
	public Role getSecretaryRole() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select r from Role r where r.roleId = 3");
		return (Role) query.uniqueResult();
	}

	@Override
	public List<Role> toModelListFromString(List<String> rolesString) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Role> roles = new ArrayList<>();
		for (String stringRole : rolesString) {
			Query query = session.createQuery("Select r from Role r where r.roleName = :stringRole");
			query.setParameter("stringRole", stringRole);
			roles.add((Role) query.uniqueResult());
		}
		return roles;
	}

	@Override
	public List<RoleDTO> rolesOfAUser(UserDTO userDTO) {
		User user = userDAO.getUserById(userDTO.getUserId());
		List<Role> roles = user.getRolesOfThisUser();
		List<RoleDTO> rolesDTO = new ArrayList<>();
		for(Role role : roles) {
			rolesDTO.add(RoleConverter.toDTO(role));
		}
		return rolesDTO;
	}

	@Override
	public List<RoleDTO> getAllRoles() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Role> roles = session.createQuery("Select r from Role r").list();
		List<RoleDTO> rolesDTO = new ArrayList<>();
		for(Role role : roles) {
			rolesDTO.add(RoleConverter.toDTO(role));
		}
		return rolesDTO;
	}

	
}

