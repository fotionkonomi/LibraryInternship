package org.lms.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;
import org.lms.dto.BookDTO;
import org.lms.dto.CategoryDTO;
import org.lms.dto.UserDTO;
import org.lms.service.BookService;
import org.lms.service.CategoryService;
import org.lms.service.ReservationService;
import org.lms.service.UserService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.dao.DataIntegrityViolationException;

@ManagedBean(name = "bookBean")
@ViewScoped
public class BookBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;
	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;
	@ManagedProperty(value = "#{reservationService}")
	private ReservationService reservationService;
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;
	private CategoryDTO categoryOfThisBook;
	private BookDTO bookDTO;
	private String categoryString;
	private BookDTO bookSelectedForReserve;
	private List<BookDTO> listAllBooks;
	private List<BookDTO> booksSelected;
	private BookDTO bookSelected;
	private List<BookDTO> listBooksBooked;
	private List<BookDTO> listBooksFree;
	private String image;

	@PostConstruct
	public void init() {
		listAllBooks = listAllBooks();
		bookDTO = new BookDTO();
		booksSelected = new ArrayList<>();
		listBooksBooked = listBooksBooked();
		listBooksFree = listBookFree();
	}

	public String getCategoryString() {
		return categoryString;
	}

	public void setCategoryString(String categoryString) {
		this.categoryString = categoryString;
	}

	public CategoryDTO getCategoryOfThisBook() {
		return categoryOfThisBook;
	}

	public void setCategoryOfThisBook(CategoryDTO categoryOfThisBook) {
		this.categoryOfThisBook = categoryOfThisBook;
	}

	public void upload(FileUploadEvent event) {
		image = event.getFile().getFileName();
		System.out.println("TEEEST: " + image);
	}

	public String addBook() {
		bookDTO.setCategoryOfThisBook(categoryService.categoryViaString(categoryString));
		bookDTO.setCreated(new Date());
		bookDTO.setModified(new Date());
		if (image != null) {
			if (image.endsWith(".png") || image.endsWith(".jpg")) {
				bookDTO.setImage(image);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "This file is not supported!", null));
				return null;
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter an image!", null));
			return null;
		}
		try {
			bookService.addBook(bookDTO);
			return "book-added?faces-redirect=true&isbn=" + bookDTO.getIsbn();
		} catch (ConstraintViolationException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Isbn is already taken", null));
			return null;
		}

	}

	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void deliverBook(BookDTO bookDTO) {
		reservationService.bookDelivering(bookDTO);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("manage-books.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//--------------------------------------------------------------
	public void reserveBooks() {
		List<BookDTO> booksNotReserved = reservationService.booksReservation(booksSelected,
				userBean.getUserDTOLogged());
		String books = "";
		for (BookDTO bookDTO : booksNotReserved) {
			books += bookDTO.getBookTitle() + " ";
		}
		if (booksNotReserved.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("You have successfully booked the selected books"));
		} else if (booksNotReserved.size() == booksSelected.size()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry, " + books + " were booked", null));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Sorry, " + books + " were booked, however, you have successfully booked the other books", null));
		}
		listAllBooks = listAllBooks();

	}

//----------------------------------------------------------------
	public void freeBook(BookDTO bookDTO) {
		reservationService.bookFree(bookDTO);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("manage-books.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public UserDTO getUserThatHasBookedTheBook(BookDTO bookDTO) {

		return reservationService.getUserThatHasBookedTheBook(bookDTO);

	}

	public UserDTO getUserThatTheBookIsDelivered(BookDTO bookDTO) {
		return reservationService.getUserThatTheBookIsDelivered(bookDTO);
	}

	public List<BookDTO> listAllBooks() {
		return bookService.listAllBooks();
	}

	public List<BookDTO> listBookFree() {
		return bookService.listBookFree();
	}

	public List<BookDTO> listBooksBooked() {
		return bookService.listBooksBooked();
	}

	public List<BookDTO> listBooksDelivered() {
		return bookService.listBooksDelivered();
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

	public String goToCreateBook() {
		return "book";
	}

	public BookDTO getBookSelectedForReserve() {
		return bookSelectedForReserve;
	}

	public void setBookSelectedForReserve(BookDTO bookSelectedForReserve) {
		this.bookSelectedForReserve = bookSelectedForReserve;
	}

	public ReservationService getReservationService() {
		return reservationService;
	}

	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	public List<BookDTO> getListAllBooks() {
		return listAllBooks;
	}

	public void setListAllBooks(List<BookDTO> listAllBooks) {
		this.listAllBooks = listAllBooks;
	}

	public List<BookDTO> getBooksSelected() {
		return booksSelected;
	}

	public void setBooksSelected(List<BookDTO> booksSelected) {
		this.booksSelected = booksSelected;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public BookDTO getBookSelected() {
		return bookSelected;
	}

	public void setBookSelected(BookDTO bookSelected) {
		this.bookSelected = bookSelected;
	}

	public String goToEditBook() {
		return "book-edit?faces-redirect=true&id=" + bookSelected.getBookId();
	}

	public List<BookDTO> getListBooksBooked() {
		return listBooksBooked;
	}

	public void setListBooksBooked(List<BookDTO> listBooksBooked) {
		this.listBooksBooked = listBooksBooked;
	}

	public List<BookDTO> getListBooksFree() {
		return listBooksFree;
	}

	public void setListBooksFree(List<BookDTO> listBooksFree) {
		this.listBooksFree = listBooksFree;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
