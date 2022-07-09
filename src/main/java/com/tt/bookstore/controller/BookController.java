package com.tt.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tt.bookstore.dto.BookCheckout;
import com.tt.bookstore.dto.BookDTO;
import com.tt.bookstore.service.BookService;
import com.tt.bookstore.utility.DataValidator;
import com.tt.bookstore.utility.ExceptionHandler;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private ExceptionHandler exception;
	@Autowired
	private DataValidator validator;

	private static final String INVALID_DATA = "INVALID_DATA";
	private static final String EXCEPTION_EXEC = "PLEASE_CONTACT_ADMIN";

	@PostMapping("/add")
	public ResponseEntity<Long> add(@RequestBody BookDTO book) {
		try {
			if(!validator.validateData(book)) {
				return new ResponseEntity(INVALID_DATA,HttpStatus.BAD_REQUEST);				
			}
			return new ResponseEntity<>(bookService.save(book),HttpStatus.OK);
		} catch (Exception e) {
			exception.writeLog(e);
			return new ResponseEntity(EXCEPTION_EXEC,HttpStatus.INTERNAL_SERVER_ERROR);
		}				
	}

	@PostMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody BookDTO book) {
		try {
			if(!validator.validateData(book)) {
				return new ResponseEntity(INVALID_DATA,HttpStatus.BAD_REQUEST);				
			}
			return new ResponseEntity<>(bookService.save(book)>0,HttpStatus.OK);
		} catch (Exception e) {
			exception.writeLog(e);			
			return new ResponseEntity(EXCEPTION_EXEC,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@PostMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		try {
			if(!validator.validateData(id)) {
				return new ResponseEntity(INVALID_DATA,HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(bookService.delete(id),HttpStatus.OK);
		} catch (Exception e) {
			exception.writeLog(e);	
			return new ResponseEntity(EXCEPTION_EXEC,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@GetMapping("/list")
	public ResponseEntity<List<BookDTO>> list() {
		try {
			return new ResponseEntity<>( bookService.getBookList(),HttpStatus.OK);
		} catch (Exception e) {
			exception.writeLog(e);
			return new ResponseEntity(EXCEPTION_EXEC,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getBook/{isbn}")
	public ResponseEntity<BookDTO> getBook(@PathVariable("isbn") Long isbn) {
		try {
			if(!validator.validateData(isbn)) {
				return new ResponseEntity(INVALID_DATA,HttpStatus.BAD_REQUEST);				
			}
			return new ResponseEntity<>(bookService.getBookByISBN(isbn),HttpStatus.OK);
		} catch (Exception e) {
			exception.writeLog(e);
			return new ResponseEntity(EXCEPTION_EXEC,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/checkoutTotal")
	public ResponseEntity<Double> checkoutTotal(@RequestBody BookCheckout bookCheckout) {
		try {
			if(!validator.validateData(bookCheckout)) {
				return new ResponseEntity(INVALID_DATA,HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(bookService.checkoutTotal(bookCheckout),HttpStatus.OK);
		} catch (Exception e) {
			exception.writeLog(e);	
			return new ResponseEntity(EXCEPTION_EXEC,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
}
