package com.tt.bookstore.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DISCOUNT")
public class Discount  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID",nullable = false)
	private Long id;
	@Column(name = "BOOK_TYPE")
	private Long bookType;//if bookType is not null than promocode is not needed
	@Column(name = "PROMOCODE")
	private String promoCode;//if promocode is there it will be applied on G.Total
	@Column(name = "DISCOUNT",nullable = false)
	private Double discount;
	@Column(name = "DISCOUNT_TYPE",nullable = false)
	private Integer discountType;//1-PERCENTAGE,2-FLAT(Applicable only on G.Total)
	@Column(name = "START_DATE",nullable = false)
	private Date startDate;
	@Column(name = "END_DATE")
	private Date endDate;
	@Column(name = "IS_ACTIVE",nullable = false)
	private Integer isActive;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBookType() {
		return bookType;
	}
	public void setBookType(Long bookType) {
		this.bookType = bookType;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Integer getDiscountType() {
		return discountType;
	}
	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
}
