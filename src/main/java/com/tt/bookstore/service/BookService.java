package com.tt.bookstore.service;

import java.util.List;

import com.tt.bookstore.dto.BookCheckout;
import com.tt.bookstore.dto.BookDTO;

public interface BookService {
	
	public List<BookDTO> getBookList()throws Exception;
	public BookDTO getBookByISBN(Long isbn)throws Exception;
	public Long save(BookDTO dto) throws Exception;
	public Boolean delete(Long id) throws Exception;
	public Double checkoutTotal(BookCheckout bookCheckout) throws Exception;
}
