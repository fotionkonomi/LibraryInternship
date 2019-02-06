package org.lms.managedbeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;
import org.lms.dto.BookDTO;
import org.lms.dto.CategoryDTO;
import org.lms.dto.UserDTO;
import org.lms.service.BookService;
import org.lms.service.CategoryService;
import org.lms.service.ReservationService;
import org.lms.service.UserService;
import org.primefaces.event.RowEditEvent;
import org.springframework.dao.DataIntegrityViolationException;

@ManagedBean(name = "bookBean")
@RequestScoped
public class BookBean {
	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;
	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;
	@ManagedProperty(value = "#{reservationService}")
	private ReservationService reservationService;
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;
	private String bookTitle;
	private String bookAuthor;
	private CategoryDTO categoryOfThisBook;
	private BookDTO bookDTO;
	private String categoryString;
	private BookDTO bookSelectedForReserve;
	private List<BookDTO> listAllBooks;
	private Integer isbn;
	private List<BookDTO> booksSelected;

	@PostConstruct
	public void init() {
		listAllBooks = listBookFree();
		bookDTO = new BookDTO();
		booksSelected = new ArrayList<>();
	}

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

	public void addBook() {
		bookDTO.setBookTitle(bookTitle);
		bookDTO.setBookAuthor(bookAuthor);
		bookDTO.setCategoryOfThisBook(categoryService.categoryViaString(categoryString));
		bookDTO.setCreated(new Date());
		bookDTO.setModified(new Date());
		bookDTO.setIsbn(isbn);
		try {
			bookService.addBook(bookDTO);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
					"Book Added!\nTitle: " + bookTitle + "\nAuthor: " + bookAuthor + "\nCategory: " + categoryString));
		} catch (ConstraintViolationException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Isbn is already taken", null));

		}

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
		listAllBooks = listBookFree();
		

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

	public void onRowEdit(RowEditEvent event) {
		BookDTO bookDTO = (BookDTO) event.getObject();
		bookDTO.setCategoryOfThisBook(categoryService.categoryViaString(categoryString));
		bookDTO.setModified(new Date());

		bookService.updateBook(bookDTO);
		FacesMessage msg = new FacesMessage("Book Edited", bookDTO.getBookTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((CategoryDTO) event.getObject()).getCategoryName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
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

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
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

}
