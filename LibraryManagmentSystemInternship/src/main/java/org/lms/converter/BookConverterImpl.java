package org.lms.converter;

import org.lms.dto.BookDTO;
import org.lms.dto.UserDTO;
import org.lms.model.Book;
import org.lms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class BookConverterImpl implements BookConverter {

	@Autowired
	private CategoryConverter categoryConverter;

	@Autowired
	private UserConverter userConverter;

	public Book toModel(BookDTO bookDTO) {
		Book book = new Book();
		Integer id = bookDTO.getBookId();
		if (id != null) {
			book.setBookId(bookDTO.getBookId());
		}
		book.setBookTitle(bookDTO.getBookTitle());
		book.setBookAuthor(bookDTO.getBookAuthor());
		book.setCategoryOfThisBook(categoryConverter.toModel(bookDTO.getCategoryOfThisBook()));
		return book;
	}

	public BookDTO toDTO(Book book) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setBookId(book.getBookId());
		bookDTO.setBookAuthor(book.getBookAuthor());
		bookDTO.setBookTitle(book.getBookTitle());
		bookDTO.setCategoryOfThisBook(categoryConverter.toDTO(book.getCategoryOfThisBook()));
		return bookDTO;
	}

	public CategoryConverter getCategoryConverter() {
		return categoryConverter;
	}

	public void setCategoryConverter(CategoryConverter categoryConverter) {
		this.categoryConverter = categoryConverter;
	}

	public UserConverter getUserConverter() {
		return userConverter;
	}

	public void setUserConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

}
