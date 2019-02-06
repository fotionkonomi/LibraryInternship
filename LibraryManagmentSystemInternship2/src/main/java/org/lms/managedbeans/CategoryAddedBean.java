package org.lms.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.lms.dto.CategoryDTO;

@ManagedBean(name = "categoryAddedBean")
@RequestScoped
public class CategoryAddedBean {

	private CategoryDTO categoryAdded;

	public CategoryDTO getCategoryAdded() {
		return categoryAdded;
	}

	public void setCategoryAdded(CategoryDTO categoryAdded) {
		this.categoryAdded = categoryAdded;
	}

	public String goToCreateCategory() {
		return ("category");
	}
}
