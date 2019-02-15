package org.lms.dao;

import org.lms.dto.BookDTO;
import org.lms.dto.ReservationDTO;
import org.lms.dto.UserDTO;

public interface ReservationDAO {

	void bookReservation(BookDTO bookDTO, UserDTO userDTO);
	
	void bookDelivering(BookDTO bookDTO);
	
	void bookFree(BookDTO bookDTO);
	
	ReservationDTO getReservationOfTheBook(BookDTO bookDTO);
		
	ReservationDTO getReservationOfTheBookDelivered(BookDTO bookDTO);
	
	UserDTO getUserThatHasBookedTheBook(BookDTO bookDTO);
	
	UserDTO getUserThatTheBookIsDelivered(BookDTO bookDTO);
	
	boolean isBookFree(BookDTO bookDTO);
}
