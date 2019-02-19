package org.lms.converter;

import org.lms.dto.CategoryDTO;
import org.lms.model.Category;

public class CategoryConverter {

	/**
	 * Converts a category from a dto to a model
	 * @param categoryDTO
	 * @return
	 */
	public static Category toModel(CategoryDTO categoryDTO) {
		Category category = new Category();
		Integer id = categoryDTO.getCategoryId();
		if (id != null) {
			category.setCategoryId(categoryDTO.getCategoryId());
		}
		category.setCategoryName(categoryDTO.getCategoryName());
		category.setCategoryDescription(categoryDTO.getCategoryDescription());
		category.setCreated(categoryDTO.getCreated());
		category.setModified(categoryDTO.getModified());
		return category;
	}

	/**
	 * Converts a category from a model to a dto
	 * @param category
	 * @return
	 */
	public static CategoryDTO toDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());
		categoryDTO.setCategoryDescription(category.getCategoryDescription());
		categoryDTO.setCreated(category.getCreated());
		categoryDTO.setModified(category.getModified());
		return categoryDTO;
	}


}
