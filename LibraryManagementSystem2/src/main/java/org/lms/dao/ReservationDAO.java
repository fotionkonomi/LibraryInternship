package org.lms.dao;

import org.lms.dto.BookDTO;
import org.lms.dto.ReservationDTO;
import org.lms.dto.UserDTO;

public interface ReservationDAO {

	/**
	 * User in the argument reserves the book in the argument
	 * @param bookDTO
	 * @param userDTO
	 */
	void bookReservation(BookDTO bookDTO, UserDTO userDTO);
	
	/**
	 * Delivers the book to its booker
	 * @param bookDTO
	 */
	void bookDelivering(BookDTO bookDTO);
	
	/**
	 * Frees a book from its booker
	 * @param bookDTO
	 */
	void bookFree(BookDTO bookDTO);
	
	/**
	 * Returns the reservation of a book
	 * @param bookDTO
	 * @return
	 */
	ReservationDTO getReservationOfTheBook(BookDTO bookDTO);
		
	/**
	 * Returns the reservation of the delivered book
	 * @param bookDTO
	 * @return
	 */
	ReservationDTO getReservationOfTheBookDelivered(BookDTO bookDTO);
	
	/**
	 * Returns the booker of a book
	 * @param bookDTO
	 * @return
	 */
	UserDTO getUserThatHasBookedTheBook(BookDTO bookDTO);
	
	/**
	 * Returns the user that the book is delivered to
	 * @param bookDTO
	 * @return
	 */
	UserDTO getUserThatTheBookIsDelivered(BookDTO bookDTO);
	
	/**
	 * Checks if a book is free
	 * @param bookDTO
	 * @return
	 */
	boolean isBookFree(BookDTO bookDTO);
}
