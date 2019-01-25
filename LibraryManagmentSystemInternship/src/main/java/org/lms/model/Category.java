package org.lms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 * 
 * @author pankaj
 *
 */
@Entity
@Table(name = "category")
public class Category {
	@Id
	@Column(name = "category_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;

	@Column(name = "category_name", nullable = false, length = 20, unique = true)
	private String categoryName;

	@Column(name = "category_description", nullable = false, length = 255)
	private String categoryDescription;

	@OneToMany(mappedBy = "categoryOfThisBook")
	private List<Book> booksOfThisCategory = new ArrayList<Book>();

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
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

	public List<Book> getBooksOfThisCategory() {
		return booksOfThisCategory;
	}

	public void setBooksOfThisCategory(List<Book> booksOfThisCategory) {
		this.booksOfThisCategory = booksOfThisCategory;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryDescription="
				+ categoryDescription + "]";
	}

}