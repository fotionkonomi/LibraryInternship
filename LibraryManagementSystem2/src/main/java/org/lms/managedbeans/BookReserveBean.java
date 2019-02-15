package org.lms.managedbeans;

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

@ManagedBean
@ViewScoped
public class BookReserveBean {

	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;
	@ManagedProperty(value = "#{reservationService}")
	private ReservationService reservationService;
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;
	private List<BookDTO> booksSelected;
	private List<BookDTO> listBooksFree;
	private BookDTO bookSelected;
	private List<BookDTO> filteredBooks;

	@PostConstruct
	public void init() {
		listBooksFree = bookService.getBookFree();
	}

	public void reserveBooks() {
		List<BookDTO> booksNotReserved = reservationService.booksReservation(booksSelected,
				userBean.getUserDTOLogged());
		String books = "";
		for (BookDTO bookDTO : booksNotReserved) {
			books += bookDTO.getBookTitle() + " ";
		}
		if (booksNotReserved.isEmpty()) {
			if (booksSelected.size() > 1) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("You have successfully booked the selected books"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("You have successfully booked the selected book"));
			}
		} else if (booksNotReserved.size() == booksSelected.size()) {
			if (booksSelected.size() > 1) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Sorry, " + books + " were booked just a moment ago", null));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Sorry, " + books + " was booked just a moment ago", null));
			}
		} else {
			if (booksNotReserved.size() > 1) {
				if (booksSelected.size() > 1) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Sorry, " + books
											+ " were booked, however, you have successfully booked the other books",
									null));

				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Sorry, " + books
											+ " were booked, however, you have successfully booked the other book",
									null));
				}
			} else {
				if (booksSelected.size() > 1) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Sorry, " + books
											+ " was booked, however, you have successfully booked the other books",
									null));
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Sorry, " + books
											+ " was booked, however, you have successfully booked the other book",
									null));
				}
			}

		}
		listBooksFree = bookService.getBookFree();
	}

	public void reserveSelectedBook() {
		BookDTO bookToReserve = booksSelected.get(0);
		if (reservationService.isBookFree(bookToReserve)) {
			reservationService.bookReservation(bookToReserve, userBean.getUserDTOLogged());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("You have successfully booked the selected book"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Sorry! The book was reserved just a moment ago!", null));
		}
		listBooksFree = bookService.getBookFree();

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

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public List<BookDTO> getBooksSelected() {
		return booksSelected;
	}

	public void setBooksSelected(List<BookDTO> booksSelected) {
		this.booksSelected = booksSelected;
	}

	public List<BookDTO> getListBooksFree() {
		return listBooksFree;
	}

	public void setListBooksFree(List<BookDTO> listBooksFree) {
		this.listBooksFree = listBooksFree;
	}

	public BookDTO getBookSelected() {
		return bookSelected;
	}

	public void setBookSelected(BookDTO bookSelected) {
		this.bookSelected = bookSelected;
	}

	public List<BookDTO> getFilteredBooks() {
		return filteredBooks;
	}

	public void setFilteredBooks(List<BookDTO> filteredBooks) {
		this.filteredBooks = filteredBooks;
	}

}
