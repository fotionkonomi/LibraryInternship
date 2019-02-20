package org.lms.managedbeans;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	private static final Logger LOGGER = Logger.getLogger(BookAddedBean.class.getName());

	/**
	 * The book that is just added to the system
	 */
	private BookDTO bookAdded;

	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String isbnString = paramMap.get("isbn");
		try {
			Integer isbn = Integer.parseInt(isbnString);
			bookAdded = bookService.getBookByISBN(isbn);
			if (bookAdded == null) {
				LOGGER.log(Level.WARNING, "Book not found");
				redirectTo404();
			}
		} catch (NumberFormatException e) {
			LOGGER.log(Level.WARNING, "Book not found");
			redirectTo404();
		}
	}

	/**
	 * Redirects to the book.xhtml page
	 * 
	 * @return
	 */
	public String goToAddBook() {
		return "book?faces-redirect=true";
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

	private void redirectTo404() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/LibraryManagementSystem/error/error-404.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
