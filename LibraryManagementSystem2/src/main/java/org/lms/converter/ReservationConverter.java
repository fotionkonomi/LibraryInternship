package org.lms.converter;

import org.lms.dto.ReservationDTO;
import org.lms.model.Reservation;

public class ReservationConverter {

	/**
	 * Converts a reservation object from a dto to a model
	 * @param reservationDTO
	 * @return
	 */
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

	/**
	 * Converts a reservation object from a model to a dto
	 * @param reservation
	 * @return
	 */
	public static ReservationDTO toDTO(Reservation reservation) {
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setReservationId(reservation.getReservationId());
		reservationDTO.setBookDTO(BookConverter.toDTO(reservation.getBook()));
		reservationDTO.setBookerDTO(UserConverter.toDTO((reservation.getBooker())));
		reservationDTO.setStatus(reservation.getStatus());
		return reservationDTO;
	}

}
