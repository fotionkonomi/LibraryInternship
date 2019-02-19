package org.lms.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id", nullable = false)
	/**
	 * Primary key of books table
	 */
	private Integer bookId;

	@Column(name = "book_title", nullable = false, length = 100)
	/**
	 * Title of the book
	 */
	private String bookTitle;

	@Column(name = "book_author", nullable = false, length = 40)
	/**
	 * The author of the book
	 */
	private String bookAuthor;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id", nullable = false)
	/**
	 * Category of this book
	 */
	private Category categoryOfThisBook;

	@Column(name = "isbn", nullable = false, unique = true)
	/**
	 * International Serial Book Number of this book. Has to be unique
	 */
	private Integer isbn;

	@Column(name = "created", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	/**
	 * The date that it is added to the system
	 */
	private Date created;

	@Column(name = "modified", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	/**
	 * The date that it is modified. If it is not modified, this field is equal to
	 * the 'created' field
	 */
	private Date modified;

	@Column(name = "image", nullable = false)
	/**
	 * Image name that associates this book
	 */
	private String image;

	@Column(name = "description", nullable = false, length = 8000)
	/**
	 * Description of this book
	 */
	private String description;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public Category getCategoryOfThisBook() {
		return categoryOfThisBook;
	}

	public void setCategoryOfThisBook(Category categoryOfThisBook) {
		this.categoryOfThisBook = categoryOfThisBook;
	}

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
