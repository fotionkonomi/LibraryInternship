package org.lms.converter;

import java.util.ArrayList;
import java.util.List;

import org.lms.dto.BookDTO;
import org.lms.dto.CategoryDTO;
import org.lms.model.Book;
import org.lms.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class CategoryConverterImpl implements CategoryConverter {

	@Autowired
	private BookConverter bookConverter;

	public Category toModel(CategoryDTO categoryDTO) {
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

	public CategoryDTO toDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());
		categoryDTO.setCategoryDescription(category.getCategoryDescription());
		categoryDTO.setCreated(category.getCreated());
		categoryDTO.setModified(category.getModified());
		return categoryDTO;
	}

	public BookConverter getBookConverter() {
		return bookConverter;
	}

	public void setBookConverter(BookConverter bookConverter) {
		this.bookConverter = bookConverter;
	}

}
