package org.lms.dao;

import java.util.*;
import org.lms.dto.BookDTO;
import org.lms.model.Book;

public interface BookDAO {

	void addBook(BookDTO bookDTO);
	
	void updateBook(BookDTO bookDTO);
	
	Book getBookById(int id);
	
	BookDTO getBookByISBN(int isbn);
		
	List<BookDTO> getAllBooks();
	
	List<BookDTO> getBookFree();
	
	List<BookDTO> getBooksBooked();
	
	List<BookDTO> getBooksDelivered();
}
