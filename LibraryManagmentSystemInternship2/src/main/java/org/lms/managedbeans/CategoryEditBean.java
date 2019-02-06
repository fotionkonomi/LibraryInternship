package org.lms.managedbeans;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.lms.dto.CategoryDTO;
import org.lms.service.CategoryService;

@ManagedBean(name = "categoryEditBean")
@ViewScoped
public class CategoryEditBean {

	private CategoryDTO categorySelected;
	
	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	public CategoryDTO getCategorySelected() {
		return categorySelected;
	}

	public void setCategorySelected(CategoryDTO categorySelected) {
		this.categorySelected = categorySelected;
	}
	
	public void editCategory() {
		categoryService.updateCategory(categorySelected);
		FacesMessage msg = new FacesMessage("Category Edited", categorySelected.getCategoryName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public String goToEdit() {
		return "edit-category";
	}
	
	public String goToCreateCategory() {
		return ("category");
	}
	
}
