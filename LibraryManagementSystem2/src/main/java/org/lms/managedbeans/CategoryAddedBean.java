package org.lms.managedbeans;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	private static final Logger LOGGER = Logger.getLogger(CategoryAddedBean.class.getName());

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		try {
			String catName = paramMap.get("name");
			categoryAdded = categoryService.categoryViaString(catName);
			LOGGER.log(Level.INFO, "Category added : " + catName);

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Category not found");

			redirectTo404();
		}
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

	private void redirectTo404() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/LibraryManagementSystem/error/error-404.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
