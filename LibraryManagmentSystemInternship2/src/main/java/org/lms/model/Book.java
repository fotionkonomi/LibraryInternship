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
	private Integer bookId;

	@Column(name = "book_title", nullable = false, length = 30)
	private String bookTitle;

	@Column(name = "book_author", nullable = false, length = 40)
	private String bookAuthor;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id", nullable = false)
	private Category categoryOfThisBook;

	@Column(name = "isbn", nullable = false, unique = true)
	private Integer isbn;

	@Column(name = "created", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "modified", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

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

}
