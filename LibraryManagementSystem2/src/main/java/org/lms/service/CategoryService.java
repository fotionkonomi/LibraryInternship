package org.lms.service;

import java.util.List;

import org.lms.dto.CategoryDTO;

public interface CategoryService {

	/**
	 * Adds a category in the system
	 * @param category
	 */
	void addCategory(CategoryDTO category);

	/**
	 * Updates an existing category in the system
	 * @param category
	 */
	void updateCategory(CategoryDTO category);

	/**
	 * Returns a list of all categories in the system
	 * @return
	 */
	List<CategoryDTO> getAllCategories();

	/**
	 * Returns a list of all category names in the system
	 * @return
	 */
	List<String> listCategoriesInString();

	/**
	 * Searches a category by its name and returns it
	 * @param categoryName
	 * @return
	 */
	CategoryDTO categoryViaString(String categoryName);

	/**
	 * Returns a category by searching it via its id
	 * @param id
	 * @return
	 */
	CategoryDTO getCategoryById(int id);
}