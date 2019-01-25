package org.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.lms.dao.CategoryDAO;
import org.lms.dto.CategoryDTO;
import org.lms.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	@Override
	@Transactional
	public void addCategory(CategoryDTO categoryDTO) {
		this.categoryDAO.addCategory(categoryDTO);
	}

	@Override
	@Transactional
	public List<CategoryDTO> listCategory() {
		return this.categoryDAO.listCategory();
	}

	@Override
	@Transactional
	public List<String> listCategoriesInString() {
		List<CategoryDTO> categoriesDTO = listCategory();
		List<String> strings = new ArrayList<String>();
		for (CategoryDTO categoryDTO : categoriesDTO) {
			strings.add(categoryDTO.getCategoryName());
		}
		return strings;

	}

	@Override
	@Transactional
	public CategoryDTO categoryViaString(String categoryName) {
		return this.categoryDAO.categoryViaCategory(categoryName);
	}
}
