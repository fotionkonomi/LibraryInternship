package org.lms.managedbeans;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.lms.dto.CategoryDTO;
import org.lms.service.CategoryService;
import org.springframework.dao.DataIntegrityViolationException;

@ManagedBean(name = "categoryEditBean")
@ViewScoped
public class CategoryEditBean {

	private CategoryDTO categorySelected;

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String catId = paramMap.get("id");
		try {
			Integer categoryId = Integer.parseInt(catId);
			try {
				categorySelected = categoryService.getCategoryById(categoryId);
			} catch (NullPointerException e) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("/error-404.xhtml");
			}

		} catch (NumberFormatException e) {
			e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public CategoryDTO getCategorySelected() {
		return categorySelected;
	}

	public void setCategorySelected(CategoryDTO categorySelected) {
		this.categorySelected = categorySelected;
	}

	public void editCategory() {
		try {
			categorySelected.setModified(new Date());
			categoryService.updateCategory(categorySelected);
			FacesMessage msg = new FacesMessage("Category Edited", categorySelected.getCategoryName());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (DataIntegrityViolationException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category already exists!", null));

		}
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String goToCreateCategory() {
		return ("category");
	}

}
