package org.lms.dto;

public class BookDTO {

	private Integer bookId;
	private String bookTitle;
	private String bookAuthor;
	private CategoryDTO categoryOfThisBook;
	private UserDTO booker;
	private Integer status;

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

	public UserDTO getBooker() {
		return booker;
	}

	public void setBooker(UserDTO booker) {
		this.booker = booker;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
