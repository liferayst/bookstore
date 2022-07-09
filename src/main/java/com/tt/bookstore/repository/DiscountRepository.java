package com.tt.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tt.bookstore.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long>{
	
	@Query("from Discount where bookType in (?1) and isActive=1")
	List<Discount> findDiscount(List<Long> bookType);
	
	@Query("from Discount where promoCode=?1 and isActive=1")
	Optional<Discount> findByPromoCode(String promoCode);
}