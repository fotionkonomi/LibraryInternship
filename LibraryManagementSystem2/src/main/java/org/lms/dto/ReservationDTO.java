package org.lms.dto;

public class ReservationDTO {

	/**
	 * A unique identifier for a reservation
	 */
	private Integer reservationId;

	/**
	 * The user that has made a reservation
	 */
	private UserDTO bookerDTO;

	/**
	 * The book that the reservation is made for
	 */
	private BookDTO bookDTO;

	/**
	 * 0 -> The book is free now 1 -> The book is booked 2 -> The book is delivered
	 * to the booker
	 */
	private Integer status;

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public UserDTO getBookerDTO() {
		return bookerDTO;
	}

	public void setBookerDTO(UserDTO bookerDTO) {
		this.bookerDTO = bookerDTO;
	}

	public BookDTO getBookDTO() {
		return bookDTO;
	}

	public void setBookDTO(BookDTO bookDTO) {
		this.bookDTO = bookDTO;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
