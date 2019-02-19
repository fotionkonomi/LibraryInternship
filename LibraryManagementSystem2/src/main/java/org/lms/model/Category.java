package org.lms.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

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
	/**
	 * Primary key of category table
	 */
	private int categoryId;

	@Column(name = "category_name", nullable = false, length = 20, unique = true)
	/**
	 * Name of the category
	 */
	private String categoryName;

	@Column(name = "created", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	/**
	 * The date that the administrators have created this category
	 */
	private Date created;

	@Column(name = "modified", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	/**
	 * The date that the administrators have edited this category. If the category
	 * hasn't been edited, this field is equal to the 'created' field
	 */
	private Date modified;

	@Column(name = "category_description", nullable = false, length = 8000)
	/**
	 * Description of a category
	 */
	private String categoryDescription;

	@OneToMany(mappedBy = "categoryOfThisBook")
	/**
	 * Books of this category
	 */
	private List<Book> booksOfThisCategory = new ArrayList<>();

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

}