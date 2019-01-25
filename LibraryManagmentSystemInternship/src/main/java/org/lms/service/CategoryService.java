package org.lms.service;

import java.util.List;

import org.lms.dto.CategoryDTO;

public interface CategoryService {

	void addCategory(CategoryDTO category);

	List<CategoryDTO> listCategory();

	List<String> listCategoriesInString();
	
	CategoryDTO categoryViaString(String categoryName);
}