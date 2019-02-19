package org.lms.converter;

import org.lms.dto.BookDTO;
import org.lms.model.Book;

public class BookConverter {

	/**
	 * Converts a book from a dto to a model
	 * 
	 * @param bookDTO
	 * @return
	 */
	public static Book toModel(BookDTO bookDTO) {
		Book book = new Book();
		Integer id = bookDTO.getBookId();
		if (id != null) {
			book.setBookId(bookDTO.getBookId());
		}
		book.setBookTitle(bookDTO.getBookTitle());
		book.setBookAuthor(bookDTO.getBookAuthor());
		book.setCategoryOfThisBook(CategoryConverter.toModel(bookDTO.getCategoryOfThisBook()));
		book.setCreated(bookDTO.getCreated());
		book.setModified(bookDTO.getModified());
		book.setIsbn(bookDTO.getIsbn());
		book.setImage(bookDTO.getImage());
		book.setDescription(bookDTO.getDescription());
		return book;
	}

	/**
	 * Converts a book from a model to a dto
	 * 
	 * @param book
	 * @return
	 */
	public static BookDTO toDTO(Book book) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setBookId(book.getBookId());
		bookDTO.setBookAuthor(book.getBookAuthor());
		bookDTO.setBookTitle(book.getBookTitle());
		bookDTO.setCategoryOfThisBook(CategoryConverter.toDTO(book.getCategoryOfThisBook()));
		bookDTO.setCreated(book.getCreated());
		bookDTO.setModified(book.getModified());
		bookDTO.setIsbn(book.getIsbn());
		bookDTO.setImage(book.getImage());
		bookDTO.setDescription(book.getDescription());
		return bookDTO;
	}

}
