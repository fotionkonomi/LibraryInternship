package org.lms.dao;

import java.util.*;
import org.lms.dto.BookDTO;

public interface BookDAO {

	void addBook(BookDTO bookDTO);
	
	List<BookDTO> listBook();
	
	
}
