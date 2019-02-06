package org.lms.converter;

import org.lms.dto.ReservationDTO;
import org.lms.model.Reservation;

public interface ReservationConverter {

	Reservation toModel(ReservationDTO reservationDTO);

	ReservationDTO toDTO(Reservation reservation);
}
