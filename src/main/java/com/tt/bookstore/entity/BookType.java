package com.tt.bookstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK_TYPE")
public class BookType implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID",nullable = false)
	private Long id;
	@Column(name = "TYPE_DESC",nullable = false)
	private String typeDesc;
	
	public BookType() {
	}
	
	public BookType(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	
	
}
