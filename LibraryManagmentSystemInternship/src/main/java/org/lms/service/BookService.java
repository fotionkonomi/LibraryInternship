package org.lms.service;

import java.util.List;

import org.lms.dto.BookDTO;

public interface BookService {

	void addBook(BookDTO bookDTO);

	List<BookDTO> listBook();

	List<BookDTO> listBooksBooked();

	List<BookDTO> listBooksDelivered();
}
