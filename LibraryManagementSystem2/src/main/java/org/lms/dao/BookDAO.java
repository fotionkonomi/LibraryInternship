package org.lms.dao;

import java.util.*;
import org.lms.dto.BookDTO;
import org.lms.model.Book;

public interface BookDAO {

	/**
	 * Adds a book in the database
	 * 
	 * @param bookDTO
	 */
	void addBook(BookDTO bookDTO);

	/**
	 * Updates an existing book in the database
	 * 
	 * @param bookDTO
	 */
	void updateBook(BookDTO bookDTO);

	/**
	 * Returns the book by its id
	 * 
	 * @param id
	 * @return
	 */
	Book getBookById(int id);

	/**
	 * Returns a book by its ISBN
	 * 
	 * @param isbn
	 * @return
	 */
	BookDTO getBookByISBN(int isbn);

	/**
	 * Returns the list of all books in the database
	 * 
	 * @return
	 */
	List<BookDTO> getAllBooks();

	/**
	 * Returns the list of all free books in the database
	 * 
	 * @return
	 */
	List<BookDTO> getBookFree();

	/**
	 * Returns the list of all booked books in the database
	 * 
	 * @return
	 */
	List<BookDTO> getBooksBooked();

	/**
	 * Returns the list of all delivered books in the database
	 * 
	 * @return
	 */
	List<BookDTO> getBooksDelivered();
}
