package org.lms.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.lms.dto.BookDTO;
import org.lms.service.BookService;

@ManagedBean(name = "bookFirstBean")
public class BookFirstBean {

	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;

	private List<BookDTO> threeRandomBooks;

	@PostConstruct
	public void init() {
		threeRandomBooks = bookService.getThreeRandomBooks();
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public List<BookDTO> getThreeRandomBooks() {
		return threeRandomBooks;
	}

	public void setThreeRandomBooks(List<BookDTO> threeRandomBooks) {
		this.threeRandomBooks = threeRandomBooks;
	}

}
