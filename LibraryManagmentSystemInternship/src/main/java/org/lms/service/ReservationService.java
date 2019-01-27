package org.lms.service;

import org.lms.dto.BookDTO;
import org.lms.dto.UserDTO;

public interface ReservationService {

	void bookReservation(BookDTO bookDTO, UserDTO userDTO);

	void bookDelivering(BookDTO bookDTO);

	void bookFree(BookDTO bookDTO);

	UserDTO getUserThatHasBookedTheBook(BookDTO bookDTO);

	UserDTO getUserThatTheBookIsDelivered(BookDTO bookDTO);

}
