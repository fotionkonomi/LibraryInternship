package org.lms.converter;

import org.lms.dto.ReservationDTO;
import org.lms.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class ReservationConverter {

	public static Reservation toModel(ReservationDTO reservationDTO) {
		Reservation reservation = new Reservation();
		Integer id = reservationDTO.getReservationId();
		if (id != null) {
			reservation.setReservationId(id);
		}
		reservation.setBook(BookConverter.toModel(reservationDTO.getBookDTO()));
		reservation.setBooker(UserConverter.toModel(reservationDTO.getBookerDTO()));
		reservation.setStatus(reservationDTO.getStatus());
		return reservation;

	}

	public static ReservationDTO toDTO(Reservation reservation) {
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setReservationId(reservation.getReservationId());
		reservationDTO.setBookDTO(BookConverter.toDTO(reservation.getBook()));
		reservationDTO.setBookerDTO(UserConverter.toDTO((reservation.getBooker())));
		reservationDTO.setStatus(reservation.getStatus());
		return reservationDTO;
	}

}
