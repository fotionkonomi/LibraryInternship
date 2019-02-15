package org.lms.service;

import java.util.List;

import org.lms.dto.BookDTO;

public interface BookService {

	void addBook(BookDTO bookDTO);

	void updateBook(BookDTO bookDTO);
	
	BookDTO getBookById(int id);
	
	BookDTO getBookByISBN(int isbn);
	
	List<BookDTO> getThreeRandomBooks();
	
	List<BookDTO> getAllBooks();
	
	List<BookDTO> getBookFree();

	List<BookDTO> getBooksBooked();

	List<BookDTO> getBooksDelivered();
	
	
}
