package org.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.lms.converter.CategoryConverter;
import org.lms.dao.CategoryDAO;
import org.lms.dto.CategoryDTO;
import org.lms.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
	public void addCategory(CategoryDTO categoryDTO) {
		 this.categoryDAO.addCategory(categoryDTO);
	}

	@Override
	public List<CategoryDTO> listCategory() {
		return this.categoryDAO.listCategory();
	}

	@Override
	public List<String> listCategoriesInString() {
		List<CategoryDTO> categoriesDTO = listCategory();
		List<String> strings = new ArrayList<>();
		for (CategoryDTO categoryDTO : categoriesDTO) {
			strings.add(categoryDTO.getCategoryName());
		}
		return strings;

	}

	@Override
	public CategoryDTO categoryViaString(String categoryName) {
		return CategoryConverter.toDTO(this.categoryDAO.categoryViaString(categoryName));
	}

	@Override
	public void updateCategory(CategoryDTO category) {
		this.categoryDAO.updateCategory(category);
	}

	@Override
	public CategoryDTO getCategoryById(int id) {
		Category category =  categoryDAO.getCategoryById(id);
		return CategoryConverter.toDTO(category);
	}
}
