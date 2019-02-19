package org.lms.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	
	private List<CategoryDTO> filteredCategories;

	@PostConstruct
	public void init() {
		allCategories = categoryService.getAllCategories();
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
		try {
			categoryService.addCategory(categoryDTO);
			return "category-added?faces-redirect=true&name=" + categoryDTO.getCategoryName();

		} catch (ConstraintViolationException e) {
			return "category-exists";
		} catch(org.hibernate.exception.DataException ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data you put was too long!", null));
			return null;
		}
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

	public List<CategoryDTO> getFilteredCategories() {
		return filteredCategories;
	}

	public void setFilteredCategories(List<CategoryDTO> filteredCategories) {
		this.filteredCategories = filteredCategories;
	}
	
	public String getShortDescription(CategoryDTO categoryDTO) {
		return categoryDTO.getCategoryDescription().substring(0, 200) + " ... ";
	}

}
