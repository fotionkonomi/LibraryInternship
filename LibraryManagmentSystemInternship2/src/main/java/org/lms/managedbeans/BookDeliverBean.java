package org.lms.managedbeans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.lms.dto.BookDTO;
import org.lms.service.BookService;
import org.lms.service.ReservationService;

@ManagedBean(name = "bookDeliverBean")
@ViewScoped
public class BookDeliverBean {

	private List<BookDTO> listBooksBooked;

	private List<BookDTO> booksSelected;

	private List<BookDTO> filteredBooks;

	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;

	@ManagedProperty(value = "#{reservationService}")
	private ReservationService reservationService;

	@PostConstruct
	public void init() {
		listBooksBooked = bookService.listBooksBooked();
	}

	public void deliverBooks() {
		for (BookDTO bookDTO : booksSelected) {
			reservationService.bookDelivering(bookDTO);
		}
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("manage-books.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage("delivered", new FacesMessage("Delivered"));
		listBooksBooked = bookService.listBooksBooked();
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

}
