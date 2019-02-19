package org.lms.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;
import org.lms.dto.BookDTO;
import org.lms.service.BookService;
import org.lms.service.CategoryService;
import org.lms.service.ReservationService;
import org.primefaces.event.FileUploadEvent;

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
	/**
	 * List of filtered books in the datatable
	 */
	private List<BookDTO> filteredBooks;

	/**
	 * The book that will be added to the system
	 */
	private BookDTO bookDTO;

	/**
	 * Category name of the book's category
	 */
	private String categoryString;

	/**
	 * All books in the system
	 */
	private List<BookDTO> listAllBooks;

	/**
	 * The book that is selected in the datatable
	 */
	private BookDTO bookSelected;

	/**
	 * The name of the image of the book
	 */
	private String image;

	@PostConstruct
	public void init() {
		listAllBooks = bookService.getAllBooks();
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

	/**
	 * Adds a book in the system
	 * 
	 * @return
	 */
	public String addBook() {
		bookDTO.setCategoryOfThisBook(categoryService.categoryViaString(categoryString));
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
		} catch (org.hibernate.exception.DataException ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data you put was too long!", null));
			return null;
		}

	}

	/**
	 * It is called when the image in the view is set
	 * 
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
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

	/**
	 * Redirects to book.xhtml
	 * 
	 * @return
	 */
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

	/**
	 * Redirects to book-edit.xhtml and puts the id of the selected book as a
	 * parameter in the URI of the page
	 * 
	 * @return
	 */
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
