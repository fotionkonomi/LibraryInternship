package org.lms.service;

import org.lms.dao.ReservationDAO;
import org.lms.dto.BookDTO;
import org.lms.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDAO reservationDAO;

	@Override
	public void bookReservation(BookDTO bookDTO, UserDTO userDTO) {
		this.reservationDAO.bookReservation(bookDTO, userDTO);
	}

	@Override
	public void bookDelivering(BookDTO bookDTO) {
		this.reservationDAO.bookDelivering(bookDTO);
	}

	@Override
	public void bookFree(BookDTO bookDTO) {
		this.reservationDAO.bookFree(bookDTO);
	}

	@Override
	public UserDTO getUserThatHasBookedTheBook(BookDTO bookDTO) {
		return reservationDAO.getUserThatHasBookedTheBook(bookDTO);
	}

	@Override
	public UserDTO getUserThatTheBookIsDelivered(BookDTO bookDTO) {
		return reservationDAO.getUserThatTheBookIsDelivered(bookDTO);
	}

	public ReservationDAO getReservationDAO() {
		return reservationDAO;
	}

	public void setReservationDAO(ReservationDAO reservationDAO) {
		this.reservationDAO = reservationDAO;
	}

}
