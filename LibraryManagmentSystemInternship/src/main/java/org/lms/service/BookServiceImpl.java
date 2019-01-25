package org.lms.service;

import java.util.List;

import org.lms.dao.BookDAO;
import org.lms.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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
	@Transactional
	public void addBook(BookDTO bookDTO) {
		this.bookDAO.addBook(bookDTO);
	}

	@Override
	@Transactional
	public List<BookDTO> listBook() {
		return bookDAO.listBook();
	}

}
