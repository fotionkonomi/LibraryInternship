package org.lms.service;

import java.util.List;

import org.lms.dto.BookDTO;

public interface BookService {

	/**
	 * Adds a book in the system
	 * @param bookDTO
	 */
	void addBook(BookDTO bookDTO);

	/**
	 * Updates an existing book in the system
	 * @param bookDTO
	 */
	void updateBook(BookDTO bookDTO);
	
	/**
	 * Gets a book by its id
	 * @param id
	 * @return
	 */
	BookDTO getBookById(int id);
	
	/**
	 * Gets a book by its ISBN
	 * @param isbn
	 * @return
	 */
	BookDTO getBookByISBN(int isbn);
	
	/**
	 * Returns a list of all books
	 * @return
	 */
	List<BookDTO> getAllBooks();
	
	/**
	 * Returns a list of all free books
	 * @return
	 */
	List<BookDTO> getBookFree();

	/**
	 * Returns a list of all booked books
	 * @return
	 */
	List<BookDTO> getBooksBooked();

	/**
	 * Returns a list of all delivered books
	 * @return
	 */
	List<BookDTO> getBooksDelivered();
	
	
}
