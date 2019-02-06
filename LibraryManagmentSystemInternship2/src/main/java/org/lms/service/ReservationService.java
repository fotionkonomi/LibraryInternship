package org.lms.service;

import java.util.List;

import org.lms.dto.BookDTO;
import org.lms.dto.UserDTO;

public interface ReservationService {

	void bookReservation(BookDTO bookDTO, UserDTO userDTO);

	List<BookDTO> booksReservation(List<BookDTO> booksDTO, UserDTO userDTO);
	
	void bookDelivering(BookDTO bookDTO);

	void bookFree(BookDTO bookDTO);

	UserDTO getUserThatHasBookedTheBook(BookDTO bookDTO);

	UserDTO getUserThatTheBookIsDelivered(BookDTO bookDTO);

}
