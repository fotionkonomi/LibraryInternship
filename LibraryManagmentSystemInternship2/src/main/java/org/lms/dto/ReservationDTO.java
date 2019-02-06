package org.lms.dto;

public class ReservationDTO {

	private Integer reservationId;
	private UserDTO bookerDTO;
	private BookDTO bookDTO;
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
