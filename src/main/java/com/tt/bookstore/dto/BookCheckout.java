package com.tt.bookstore.dto;

import java.io.Serializable;
import java.util.List;

public class BookCheckout implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Long> isbns;
	private String promoCode;
	
	public BookCheckout() {
		super();
	}

	public BookCheckout(List<Long> isbns, String promoCode) {
		super();
		this.isbns = isbns;
		this.promoCode = promoCode;
	}

	public List<Long> getIsbns() {
		return isbns;
	}

	public void setIsbns(List<Long> isbns) {
		this.isbns = isbns;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
}
