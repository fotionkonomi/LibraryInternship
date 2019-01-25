package org.lms.converter;

import org.lms.dto.ReservationDTO;
import org.lms.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationConverterImpl implements ReservationConverter {

	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private BookConverter bookConverter;

	@Override
	public Reservation toModel(ReservationDTO reservationDTO) {
		Reservation reservation = new Reservation();
		Integer id = reservationDTO.getReservationId();
		if (id != null) {
			reservation.setReservationId(id);
		}
		reservation.setBook(bookConverter.toModel(reservationDTO.getBookDTO()));
		reservation.setBooker(userConverter.toModel(reservationDTO.getBookerDTO()));
		return reservation;

	}

	@Override
	public ReservationDTO toDTO(Reservation reservation) {
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setBookDTO(bookConverter.toDTO(reservation.getBook()));
		reservationDTO.setBookerDTO(userConverter.toDTO((reservation.getBooker())));
		return reservationDTO;
	}

	public UserConverter getUserConverter() {
		return userConverter;
	}

	public void setUserConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

	public BookConverter getBookConverter() {
		return bookConverter;
	}

	public void setBookConverter(BookConverter bookConverter) {
		this.bookConverter = bookConverter;
	}

}
