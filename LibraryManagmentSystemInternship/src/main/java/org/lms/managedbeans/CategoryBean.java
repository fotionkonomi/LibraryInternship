package org.lms.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.lms.dto.CategoryDTO;
import org.lms.model.Category;
import org.lms.service.CategoryService;

@ManagedBean(name = "categoryBean")
public class CategoryBean {

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	private String categoryName;

	private String categoryDescription;

	private CategoryDTO categoryDTO = new CategoryDTO();

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
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

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String addCategory() {
		categoryDTO.setCategoryName(categoryName);
		categoryDTO.setCategoryDescription(categoryDescription);
		categoryService.addCategory(categoryDTO);
		return ("added");
	}

	public List<CategoryDTO> listCategories() {
		return categoryService.listCategory();
	}

	public List<String> listCategoriesString() {
		return categoryService.listCategoriesInString();
	}
}
