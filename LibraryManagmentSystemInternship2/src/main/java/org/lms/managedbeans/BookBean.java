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
	private List<BookDTO> filteredBooks;
	private BookDTO bookDTO;
	private String categoryString;
	private List<BookDTO> listAllBooks;
	private BookDTO bookSelected;
	private String image;

	@PostConstruct
	public void init() {
		listAllBooks = listAllBooks();
		bookDTO = new BookDTO();
	}

	public String getCategoryString() {
		return categoryString;
	}

	public void setCategoryString(String categoryString) {
		this.categoryString = categoryString;
	}
	
	public void upload(FileUploadEvent event) {
		image = event.getFile().getFileName();
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

	
//--------------------------------------------------------------

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
		return "book?faces-redirect=true";
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

	public BookDTO getBookSelected() {
		return bookSelected;
	}

	public void setBookSelected(BookDTO bookSelected) {
		this.bookSelected = bookSelected;
	}

	public String goToEditBook() {
		return "book-edit?faces-redirect=true&id=" + bookSelected.getBookId();
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<BookDTO> getFilteredBooks() {
		return filteredBooks;
	}

	public void setFilteredBooks(List<BookDTO> filteredBooks) {
		this.filteredBooks = filteredBooks;
	}

}
