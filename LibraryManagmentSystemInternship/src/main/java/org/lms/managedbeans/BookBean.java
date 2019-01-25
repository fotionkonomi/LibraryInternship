package org.lms.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.lms.dto.BookDTO;
import org.lms.dto.CategoryDTO;
import org.lms.service.BookService;
import org.lms.service.CategoryService;

@ManagedBean(name = "bookBean")
public class BookBean {
	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;
	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;
	private String bookTitle;
	private String bookAuthor;
	private CategoryDTO categoryOfThisBook;
	private BookDTO bookDTO = new BookDTO();
	private String categoryString;

	public String getCategoryString() {
		return categoryString;
	}

	public void setCategoryString(String categoryString) {
		this.categoryString = categoryString;
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

	public String addBook() {
		bookDTO.setBookTitle(bookTitle);
		bookDTO.setBookAuthor(bookAuthor);
		bookDTO.setCategoryOfThisBook(categoryService.categoryViaString(categoryString));
		bookService.addBook(bookDTO);
		return ("added");
	}
	
	public List<BookDTO> listBook() {
		return bookService.listBook();
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public BookDTO getBookDTO() {
		return bookDTO;
	}

	public void setBookDTO(BookDTO bookDTO) {
		this.bookDTO = bookDTO;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	

}
