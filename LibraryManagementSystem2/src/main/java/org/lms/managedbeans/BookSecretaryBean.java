package org.lms.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.lms.dto.BookDTO;
import org.lms.dto.UserDTO;
import org.lms.service.BookService;
import org.lms.service.ReservationService;

@ManagedBean(name = "bookSecretaryBean")
@ViewScoped
public class BookSecretaryBean {

	private List<BookDTO> listBooksBooked;

	private List<BookDTO> booksSelected;

	private List<BookDTO> filteredBooks;

	private List<BookDTO> listBooksDelivered;

	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;

	@ManagedProperty(value = "#{reservationService}")
	private ReservationService reservationService;

	@PostConstruct
	public void init() {
		listBooksBooked = bookService.getBooksBooked();
		listBooksDelivered = bookService.getBooksDelivered();
	}

	public void deliverBooks() {
		for (BookDTO bookDTO : booksSelected) {
			reservationService.bookDelivering(bookDTO);
		}
		FacesContext.getCurrentInstance().addMessage("delivered", new FacesMessage("Delivered"));
		listBooksBooked = bookService.getBooksBooked();
	}

	public void freeBooks() {
		for (BookDTO bookDTO : booksSelected) {
			reservationService.bookFree(bookDTO);
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Freed!"));
		setListBooksDelivered(bookService.getBooksDelivered());
	}

	public List<BookDTO> getListBooksBooked() {
		return listBooksBooked;
	}

	public void setListBooksBooked(List<BookDTO> listBooksBooked) {
		this.listBooksBooked = listBooksBooked;
	}

	public List<BookDTO> getBooksSelected() {
		return booksSelected;
	}

	public void setBooksSelected(List<BookDTO> booksSelected) {
		this.booksSelected = booksSelected;
	}

	public List<BookDTO> getFilteredBooks() {
		return filteredBooks;
	}

	public void setFilteredBooks(List<BookDTO> filteredBooks) {
		this.filteredBooks = filteredBooks;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public ReservationService getReservationService() {
		return reservationService;
	}

	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	public UserDTO getUserThatHasBookedTheBook(BookDTO bookDTO) {
		return reservationService.getUserThatHasBookedTheBook(bookDTO);
	}
	
	public UserDTO getUserThatHasTheBook(BookDTO bookDTO) {
		return reservationService.getUserThatTheBookIsDelivered(bookDTO);
	}

	public List<BookDTO> getListBooksDelivered() {
		return listBooksDelivered;
	}

	public void setListBooksDelivered(List<BookDTO> listBooksDelivered) {
		this.listBooksDelivered = listBooksDelivered;
	}
}
