package org.lms.managedbeans;

import java.io.IOException;
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
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "bookEditBean")
@ViewScoped
public class BookEditBean {

	private BookDTO bookSelected;
	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;
	private String categoryString;
	private String image;
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
		} catch (NullPointerException e) {
			redirectTo404();

		} catch (NumberFormatException e) {
			redirectTo404();
		} catch(org.hibernate.exception.DataException ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data you put was too long!", null));
			return;
		}
	}

	public void upload(FileUploadEvent event) {
		image = event.getFile().getFileName();
	}

	public void editBook() {
		bookSelected.setModified(new Date());
		bookSelected.setCategoryOfThisBook(categoryService.categoryViaString(categoryString));
		if (image != null) {
			if (image.endsWith(".png") || image.endsWith(".jpg")) {
				bookSelected.setImage(image);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "This file is not supported!", null));
				return;
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter an image!", null));
			return;
		}
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
