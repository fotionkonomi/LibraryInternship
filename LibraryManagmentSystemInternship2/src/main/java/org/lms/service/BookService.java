package org.lms.service;

import java.util.List;

import org.lms.dto.BookDTO;

public interface BookService {

	void addBook(BookDTO bookDTO);

	void updateBook(BookDTO bookDTO);
	
	BookDTO getBookById(int id);
	
	BookDTO getBookByISBN(int isbn);
	
	List<BookDTO> listAllBooks();
	
	List<BookDTO> listBookFree();

	List<BookDTO> listBooksBooked();

	List<BookDTO> listBooksDelivered();
	
	
}
