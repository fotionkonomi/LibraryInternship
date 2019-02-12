package org.lms.service;

import java.util.ArrayList;
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
		this.bookDAO.addBook(bookDTO);
	}

	@Override
	public List<BookDTO> listBookFree() {
		return bookDAO.listBookFree();
	}

	@Override
	public List<BookDTO> listBooksBooked() {
		return bookDAO.listBooksBooked();
	}

	@Override
	public List<BookDTO> listBooksDelivered() {
		return bookDAO.listBooksDelivered();
	}

	@Override
	public List<BookDTO> listAllBooks() {
		return bookDAO.listAllBooks();
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

	@Override
	public List<BookDTO> getThreeRandomBooks() {
		List<BookDTO> three = new ArrayList<>();
		List<BookDTO> allBooks = listAllBooks();
		while(three.size() < 3){
			int index = (int)(Math.random() * allBooks.size());
			BookDTO bookDTO = allBooks.get(index);
			if(three.contains(bookDTO)) {
				continue;
			} else {
				three.add(bookDTO);
			}
		}
		return three;
	}

}
