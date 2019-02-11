package org.lms.dao;

import org.lms.converter.BookConverter;
import org.lms.dto.BookDTO;
import org.lms.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Repository
public class BookDAOImpl implements BookDAO {

	private SessionFactory sessionFactory;

	@Autowired
	private CategoryDAO categoryDAO;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addBook(BookDTO bookDTO) {
		Session session = this.sessionFactory.getCurrentSession();
		Book book = BookConverter.toModel(bookDTO);
		book.setCategoryOfThisBook(this.categoryDAO.getCategoryById(bookDTO.getCategoryOfThisBook().getCategoryId()));
		session.persist(book);
	}

	@Override
	public Book getBookById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select b from Book b where b.bookId=:bookId");
		query.setParameter("bookId", id);
		return (Book) query.uniqueResult();
	}

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public List<BookDTO> listAllBooks() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select b from Book b");
		List<Book> books = query.list();
		List<BookDTO> booksDTO = new ArrayList<>();
		for (Book book : books) {
			booksDTO.add(BookConverter.toDTO(book));
		}
		return booksDTO;
	}

	@Override
	public List<BookDTO> listBookFree() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"Select b from Book b Where b Not In (Select r.book From Reservation r where r.status = 1 or r.status = 2)");
		List<Book> books = query.list();
		List<BookDTO> booksDTO = new ArrayList<>();
		for (Book book : books) {
			booksDTO.add(BookConverter.toDTO(book));
		}
		return booksDTO;
	}

	@Override
	public List<BookDTO> listBooksBooked() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("Select b from Book b where b In (Select r.book From Reservation r where r.status = 1)");
		List<Book> books = query.list();
		List<BookDTO> booksDTO = new ArrayList<>();
		for (Book book : books) {
			booksDTO.add(BookConverter.toDTO(book));
		}
		return booksDTO;
	}

	@Override
	public List<BookDTO> listBooksDelivered() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("Select b from Book b where b In (Select r.book From Reservation r where r.status = 2)");
		List<Book> books = query.list();
		List<BookDTO> booksDTO = new ArrayList<>();
		for (Book book : books) {
			booksDTO.add(BookConverter.toDTO(book));
		}
		return booksDTO;
	}

	@Override
	public void updateBook(BookDTO bookDTO) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(BookConverter.toModel(bookDTO));
	}

	@Override
	public BookDTO getBookByISBN(int isbn) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("Select b from Book b where b.isbn = :isbn");
		query.setParameter("isbn", isbn);
		Book book = (Book) query.uniqueResult();
		return BookConverter.toDTO(book);
	}

}
