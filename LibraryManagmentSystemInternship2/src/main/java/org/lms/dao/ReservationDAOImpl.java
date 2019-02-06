package org.lms.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.lms.converter.BookConverter;
import org.lms.converter.ReservationConverter;
import org.lms.converter.UserConverter;
import org.lms.dto.BookDTO;
import org.lms.dto.ReservationDTO;
import org.lms.dto.UserDTO;
import org.lms.model.Book;
import org.lms.model.Reservation;
import org.lms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDAOImpl implements ReservationDAO {

	private SessionFactory sessionFactory;

	@Autowired
	private ReservationConverter reservationConverter;

	@Autowired
	private BookConverter bookConverter;
	
	@Autowired
	private UserConverter userConverter;

	@Autowired
	private BookDAO bookDAO;

	@Override
	public void bookReservation(BookDTO bookDTO, UserDTO userDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setBookDTO(bookDTO);
		reservationDTO.setBookerDTO(userDTO);
		reservationDTO.setStatus(1);
		session.persist(reservationConverter.toModel(reservationDTO));
	}

	@Override
	public void bookDelivering(BookDTO bookDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		ReservationDTO reservationDTO = getReservationOfTheBook(bookDTO);
		reservationDTO.setStatus(2);
		session.merge(reservationConverter.toModel(reservationDTO));
	}
	
	@Override
	public void bookFree(BookDTO bookDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		ReservationDTO reservationDTO = getReservationOfTheBookDelivered(bookDTO);
		reservationDTO.setStatus(0);
		session.merge(reservationConverter.toModel(reservationDTO));
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ReservationConverter getReservationConverter() {
		return reservationConverter;
	}

	public void setReservationConverter(ReservationConverter reservationConverter) {
		this.reservationConverter = reservationConverter;
	}

	@Override
	public ReservationDTO getReservationOfTheBook(BookDTO bookDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		Book book = bookConverter.toModel(bookDTO);
		Query query = session.createQuery("Select r from Reservation r where r.book = :book and r.status=1");
		query.setParameter("book", book);
		Reservation reservation = (Reservation) query.uniqueResult();
		return reservationConverter.toDTO(reservation);
	}
	
	@Override
	public UserDTO getUserThatHasBookedTheBook(BookDTO bookDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		Book book = bookConverter.toModel(bookDTO);
		Query query = session.createQuery("Select r from Reservation r where r.book = :book and r.status=1");
		query.setParameter("book", book);
		Reservation reservation = (Reservation) query.uniqueResult();
		return reservationConverter.toDTO(reservation).getBookerDTO();
	}

	@Override
	public UserDTO getUserThatTheBookIsDelivered(BookDTO bookDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		Book book = bookConverter.toModel(bookDTO);
		Query query = session.createQuery("Select r from Reservation r where r.book = :book and r.status=2");
		query.setParameter("book", book);
		Reservation reservation = (Reservation) query.uniqueResult();
		return reservationConverter.toDTO(reservation).getBookerDTO();
	}

	@Override
	public ReservationDTO getReservationOfTheBookDelivered(BookDTO bookDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		Book book = bookDAO.getBookById(bookDTO);
		Query query = session.createQuery("Select r from Reservation r where r.book = :book and r.status=2");
		query.setParameter("book", book);
		Reservation reservation = (Reservation) query.uniqueResult();
		return reservationConverter.toDTO(reservation);
	}

	public BookConverter getBookConverter() {
		return bookConverter;
	}

	public void setBookConverter(BookConverter bookConverter) {
		this.bookConverter = bookConverter;
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	public UserConverter getUserConverter() {
		return userConverter;
	}

	public void setUserConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

	


}
