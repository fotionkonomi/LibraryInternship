package org.lms.dao;

import java.util.*;
import org.lms.dto.BookDTO;
import org.lms.model.Book;

public interface BookDAO {

	void addBook(BookDTO bookDTO);
	
	void updateBook(BookDTO bookDTO);
	
	Book getBookById(int id);
	
	BookDTO getBookByISBN(int isbn);
	
	List<BookDTO> listAllBooks();
	
	List<BookDTO> listBookFree();
	
	List<BookDTO> listBooksBooked();
	
	List<BookDTO> listBooksDelivered();
}
