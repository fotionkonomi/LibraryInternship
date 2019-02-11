package org.lms.managedbeans;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.lms.dto.BookDTO;
import org.lms.service.BookService;

@ManagedBean(name = "bookAddedBean")
@ViewScoped
public class BookAddedBean {

	private BookDTO bookAdded;

	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String isbnString = paramMap.get("isbn");
		Integer isbn = Integer.parseInt(isbnString);
		bookAdded = bookService.getBookByISBN(isbn);
	}

	public String goToAddBook() {
		return "book";
	}

	public BookDTO getBookAdded() {
		return bookAdded;
	}

	public void setBookAdded(BookDTO bookAdded) {
		this.bookAdded = bookAdded;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
