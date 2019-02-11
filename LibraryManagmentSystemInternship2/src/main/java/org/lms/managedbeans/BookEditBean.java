package org.lms.managedbeans;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.lms.dto.BookDTO;
import org.lms.service.BookService;
import org.lms.service.CategoryService;

@ManagedBean(name="bookEditBean")
@ViewScoped
public class BookEditBean {

	private BookDTO bookSelected;
	@ManagedProperty(value="#{bookService}")
	private BookService bookService;
	private String categoryString;

	@ManagedProperty(value = "#{categoryService}")
	private CategoryService categoryService;

	@PostConstruct
	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
		String bookId = paramMap.get("id");
		try {
			int id = Integer.parseInt(bookId);
			bookSelected = bookService.getBookById(id);
			setCategoryString(bookSelected.getCategoryOfThisBook().getCategoryName());
		} catch (NumberFormatException e) {
			e.getMessage();
		}
	}

	public void editBook() {
		bookSelected.setModified(new Date());
		bookSelected.setCategoryOfThisBook(categoryService.categoryViaString(categoryString));
		bookService.updateBook(bookSelected);
		FacesMessage msg = new FacesMessage("Book Edited", bookSelected.getBookTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public BookDTO getBookSelected() {
		return bookSelected;
	}

	public void setBookSelected(BookDTO bookSelected) {
		this.bookSelected = bookSelected;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public String getCategoryString() {
		return categoryString;
	}

	public void setCategoryString(String categoryString) {
		this.categoryString = categoryString;
	}

	public List<String> allCategoriesString() {
		return categoryService.listCategoriesInString();
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
}
