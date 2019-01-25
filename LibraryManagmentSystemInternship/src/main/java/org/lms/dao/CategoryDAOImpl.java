package org.lms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lms.converter.CategoryConverter;
import org.lms.converter.UserConverter;
import org.lms.dto.CategoryDTO;
import org.lms.model.Category;

import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	private SessionFactory sessionFactory;

	private CategoryConverter categoryConverter;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addCategory(CategoryDTO categoryDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(categoryConverter.toModel(categoryDTO));
	}

	@Override
	public List<CategoryDTO> listCategory() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Category> categoryList = session.createQuery("Select c from Category c").list();
		List<CategoryDTO> categoryDTOList = new ArrayList<CategoryDTO>();
		for (Category category : categoryList) {
			categoryDTOList.add(categoryConverter.toDTO(category));
		}

		for (CategoryDTO categoryDTO : categoryDTOList) {
			System.out.println(categoryDTO.getCategoryName());
		}
		return categoryDTOList;

	}

	public CategoryConverter getCategoryConverter() {
		return categoryConverter;
	}

	public void setCategoryConverter(CategoryConverter categoryConverter) {
		this.categoryConverter = categoryConverter;
	}

	@Override
	public CategoryDTO categoryViaCategory(String categoryName) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select c from Category c where c.categoryName = :categoryName");
		query.setParameter("categoryName", categoryName);
		Category category = (Category)query.uniqueResult();
		return categoryConverter.toDTO(category);
	}
	
	@Override
	public Category getCategoryById(CategoryDTO categoryDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select c from Category c where c.categoryId = :categoryId");
		query.setParameter("categoryId", categoryDTO.getCategoryId());
		Category category = (Category)query.uniqueResult();
		return category;
	}

}
