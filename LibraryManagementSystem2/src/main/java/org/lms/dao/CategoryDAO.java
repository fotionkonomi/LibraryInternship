package org.lms.dao;

import java.util.List;

import org.lms.dto.CategoryDTO;
import org.lms.model.Category;

public interface CategoryDAO {
	/**
	 * Adds a category in the database only if it doesn't already exist there
	 * 
	 * @param category
	 */
	public void addCategory(CategoryDTO category);

	/**
	 * Updates an existing category in the database only if its attributes do not
	 * already exist in another category
	 * 
	 * @param category
	 */
	void updateCategory(CategoryDTO category);

	/**
	 * Returns the list of all categories in the database
	 * 
	 * @return
	 */
	public List<CategoryDTO> getAllCategories();

	/**
	 * Searches a category using its name and returns it
	 * 
	 * @param categoryName
	 * @return
	 */
	Category categoryViaString(String categoryName);

	/**
	 * Returns a category using its id as a criteria
	 * 
	 * @param id
	 * @return
	 */
	Category getCategoryById(int id);
}