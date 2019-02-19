package org.lms.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lms.converter.UserConverter;
import org.lms.dto.UserDTO;
import org.lms.model.User;
import org.lms.utils.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserDTO login(String username, String password) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("Select u from User u where u.username LIKE :username AND u.password LIKE :password");
		query.setString("username", username);
		query.setString("password", Encryptor.encrypt(password, 12));
		User user = (User) query.uniqueResult();
		if (user != null) {
			return UserConverter.toDTO(user);
		}
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
