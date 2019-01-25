package org.lms.converter;

import org.lms.dto.CategoryDTO;
import org.lms.model.Category;

public interface CategoryConverter {

	CategoryDTO toDTO(Category category);

	Category toModel(CategoryDTO categoryDTO);
}
