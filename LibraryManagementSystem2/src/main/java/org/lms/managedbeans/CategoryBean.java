package org.lms.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.ConstraintViolationException;
import org.lms.dto.CategoryDTO;
import org.lms.service.CategoryService;

@ManagedBean(name = "categoryBean")
@ViewScoped
public class CategoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	private CategoryDTO categoryDTO;

	private CategoryDTO selectedCategory;

	private List<CategoryDTO> allCategories;

	@PostConstruct
	public void init() {
		allCategories = listCategories();
		categoryDTO = new CategoryDTO();
		selectedCategory = new CategoryDTO();
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String addCategory() {
		categoryDTO.setCreated(new Date());
		categoryDTO.setModified(new Date());
		try {
			categoryService.addCategory(categoryDTO);
			return "category-added?faces-redirect=true&name=" + categoryDTO.getCategoryName();

		} catch (ConstraintViolationException e) {
			return "category-exists";
		}
	}

	public List<CategoryDTO> listCategories() {
		return categoryService.listCategory();
	}

	public List<String> listCategoriesString() {
		return categoryService.listCategoriesInString();
	}

	public String goToAddCategory() {
		return "add-category";
	}

	public List<CategoryDTO> getAllCategories() {
		return allCategories;
	}

	public void setAllCategories(List<CategoryDTO> allCategories) {
		this.allCategories = allCategories;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CategoryDTO getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(CategoryDTO selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
	
	public String goToEdit() {
	    return "edit-category?faces-redirect=true&id=" + selectedCategory.getCategoryId();
	}

}
