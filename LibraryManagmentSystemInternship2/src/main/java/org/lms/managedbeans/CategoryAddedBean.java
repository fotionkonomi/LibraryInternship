package org.lms.managedbeans;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.lms.dto.CategoryDTO;
import org.lms.service.CategoryService;

@ManagedBean(name = "categoryAddedBean")
@ViewScoped
public class CategoryAddedBean {

	private CategoryDTO categoryAdded;

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String catName = paramMap.get("name");
		categoryAdded = categoryService.categoryViaString(catName);
	}

	public CategoryDTO getCategoryAdded() {
		return categoryAdded;
	}

	public void setCategoryAdded(CategoryDTO categoryAdded) {
		this.categoryAdded = categoryAdded;
	}

	public String goToCreateCategory() {
		return ("category");
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

}
