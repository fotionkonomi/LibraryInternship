package org.lms.service;

import java.util.Date;
import java.util.List;

import org.lms.converter.BookConverter;
import org.lms.dao.BookDAO;
import org.lms.dto.BookDTO;
import org.lms.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO;

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	@Override
	public void addBook(BookDTO bookDTO) {
		bookDTO.setCreated(new Date());
		bookDTO.setModified(new Date());
		this.bookDAO.addBook(bookDTO);
	}

	@Override
	public List<BookDTO> getBookFree() {
		return bookDAO.getBookFree();
	}

	@Override
	public List<BookDTO> getBooksBooked() {
		return bookDAO.getBooksBooked();
	}

	@Override
	public List<BookDTO> getBooksDelivered() {
		return bookDAO.getBooksDelivered();
	}

	@Override
	public List<BookDTO> getAllBooks() {
		return bookDAO.getAllBooks();
	}

	@Override
	public void updateBook(BookDTO bookDTO) {
		bookDAO.updateBook(bookDTO);
	}

	@Override
	public BookDTO getBookById(int id) {
		Book book = bookDAO.getBookById(id);
		return BookConverter.toDTO(book);
	}

	@Override
	public BookDTO getBookByISBN(int isbn) {
		return bookDAO.getBookByISBN(isbn);
	}

}
