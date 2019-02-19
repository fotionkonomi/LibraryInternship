package org.lms.service;

import java.util.List;

import org.lms.dto.BookDTO;
import org.lms.dto.UserDTO;

public interface ReservationService {

	/**
	 * Reserves the book for the user
	 * 
	 * @param bookDTO
	 * @param userDTO
	 */
	void bookReservation(BookDTO bookDTO, UserDTO userDTO);

	/**
	 * Returns a list of all the books that are not reserved from the list of books
	 * given in the arguments
	 * 
	 * @param booksDTO
	 * @param userDTO
	 * @return
	 */
	List<BookDTO> booksReservation(List<BookDTO> booksDTO, UserDTO userDTO);

	/**
	 * Checks if a book is free
	 * @param bookDTO
	 * @return
	 */
	boolean isBookFree(BookDTO bookDTO);

	/**
	 * Delivers a book to its booker
	 * @param bookDTO
	 */
	void bookDelivering(BookDTO bookDTO);

	/**
	 * Frees a book from its booker
	 * @param bookDTO
	 */
	void bookFree(BookDTO bookDTO);

	/**
	 * Returns the user that has booked this book
	 * @param bookDTO
	 * @return
	 */
	UserDTO getUserThatHasBookedTheBook(BookDTO bookDTO);

	/**
	 * Returns the user that the book has been delivered to
	 * @param bookDTO
	 * @return
	 */
	UserDTO getUserThatTheBookIsDelivered(BookDTO bookDTO);

}
