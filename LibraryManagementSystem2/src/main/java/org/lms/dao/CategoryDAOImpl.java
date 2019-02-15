package org.lms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lms.converter.CategoryConverter;
import org.lms.dto.CategoryDTO;
import org.lms.model.Category;

import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addCategory(CategoryDTO categoryDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(CategoryConverter.toModel(categoryDTO));
			
	}

	@Override
	public List<CategoryDTO> listCategory() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Category> categoryList = session.createQuery("Select c from Category c").list();
		List<CategoryDTO> categoryDTOList = new ArrayList<>();
		for (Category category : categoryList) {
			categoryDTOList.add(CategoryConverter.toDTO(category));
		}
		return categoryDTOList;

	}

	@Override
	public Category categoryViaString(String categoryName) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select c from Category c where c.categoryName = :categoryName");
		query.setParameter("categoryName", categoryName);
		return (Category) query.uniqueResult();
	}

	@Override
	public Category getCategoryById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select c from Category c where c.categoryId = :categoryId");
		query.setParameter("categoryId", id);
		return (Category) query.uniqueResult();
	}

	@Override
	public void updateCategory(CategoryDTO category) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(CategoryConverter.toModel(category));
	}

}
