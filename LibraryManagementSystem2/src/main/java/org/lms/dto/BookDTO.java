package org.lms.dto;

import java.util.Date;

public class BookDTO {

	/**
	 * A unique identifier for a book
	 */
	private Integer bookId;

	/**
	 * Title of the book
	 */
	private String bookTitle;

	/**
	 * Author of the book
	 */
	private String bookAuthor;

	/**
	 * Category of this book
	 */
	private CategoryDTO categoryOfThisBook;

	/**
	 * International Serial Book Number of this book. Has to be Unique!
	 */
	private Integer isbn;

	/**
	 * The date when the administrators have added this book to the system
	 */
	private Date created;

	/**
	 * The date when the administrators have edited this book. If it is not edited,
	 * this field contains the date when the book has been added to the system
	 */
	private Date modified;
	
	/**
	 * Contains the name of the image that associates this book
	 */
	private String image;
	
	/**
	 * Description of this book
	 */
	private String description;

	public BookDTO() {

	}

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

	public CategoryDTO getCategoryOfThisBook() {
		return categoryOfThisBook;
	}

	public void setCategoryOfThisBook(CategoryDTO categoryOfThisBook) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookAuthor == null) ? 0 : bookAuthor.hashCode());
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((bookTitle == null) ? 0 : bookTitle.hashCode());
		result = prime * result + ((categoryOfThisBook == null) ? 0 : categoryOfThisBook.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((modified == null) ? 0 : modified.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BookDTO))
			return false;
		BookDTO other = (BookDTO) obj;
		if (bookAuthor == null) {
			if (other.bookAuthor != null)
				return false;
		} else if (!bookAuthor.equals(other.bookAuthor))
			return false;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (bookTitle == null) {
			if (other.bookTitle != null)
				return false;
		} else if (!bookTitle.equals(other.bookTitle))
			return false;
		if (categoryOfThisBook == null) {
			if (other.categoryOfThisBook != null)
				return false;
		} else if (!categoryOfThisBook.equals(other.categoryOfThisBook))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (modified == null) {
			if (other.modified != null)
				return false;
		} else if (!modified.equals(other.modified))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookDTO [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor
				+ ", categoryOfThisBook=" + categoryOfThisBook + ", isbn=" + isbn + ", created=" + created
				+ ", modified=" + modified + "]";
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

	public String getShortDescription() {
		if (description.length() < 350) {
			return description;
		} else {
			String shortDescription = description.substring(0, 350);
			shortDescription += "...";
			return shortDescription;
		}
	}

}
