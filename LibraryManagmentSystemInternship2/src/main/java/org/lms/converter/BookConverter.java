package org.lms.converter;

import org.lms.dto.BookDTO;
import org.lms.model.Book;

public interface BookConverter {

	BookDTO toDTO(Book book);
	Book toModel(BookDTO bookDTO);
}
