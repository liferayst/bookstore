package com.tt.bookstore.dto;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;

public class BookDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	private String author;
	private Long bookType;
	private String bookTypeDesc;
	private Double price;
	private Long isbn;
	
	public BookDTO() {
		
	}
	
	public BookDTO(Long id, String name, String description, String author, Long bookType,
			Double price, Long isbn) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.author = author;
		this.bookType = bookType;
		this.price = price;
		this.isbn = isbn;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Long getBookType() {
		return bookType;
	}
	public void setBookType(Long bookType) {
		this.bookType = bookType;
	}	
	public String getBookTypeDesc() {
		return bookTypeDesc;
	}
	public void setBookTypeDesc(String bookTypeDesc) {
		this.bookTypeDesc = bookTypeDesc;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getIsbn() {
		return isbn;
	}
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
}
