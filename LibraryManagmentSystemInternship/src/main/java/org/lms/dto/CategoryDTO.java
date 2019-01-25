package org.lms.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO {

	private Integer categoryId;
	private String categoryName;
	private String categoryDescription;
	//private List<BookDTO> booksOfThisCategory = new ArrayList<>();

	public CategoryDTO() {

	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	/*public List<BookDTO> getBooksOfThisCategory() {
		return booksOfThisCategory;
	}

	public void setBooksOfThisCategory(List<BookDTO> booksOfThisCategory) {
		this.booksOfThisCategory = booksOfThisCategory;
	}*/

}
