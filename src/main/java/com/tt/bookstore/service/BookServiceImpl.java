package com.tt.bookstore.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tt.bookstore.dto.BookCheckout;
import com.tt.bookstore.dto.BookDTO;
import com.tt.bookstore.entity.Book;
import com.tt.bookstore.entity.BookType;
import com.tt.bookstore.entity.Discount;
import com.tt.bookstore.repository.BookRepository;
import com.tt.bookstore.repository.DiscountRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private DiscountRepository discountRepository;
	
	@Override
	public List<BookDTO> getBookList() throws Exception {
		List<Book> list = bookRepository.findAll();		
		List<BookDTO> data = new ArrayList<>();
		for (com.tt.bookstore.entity.Book book : list) {
			com.tt.bookstore.dto.BookDTO dto = new BookDTO();
			BeanUtils.copyProperties(book, dto);
			dto.setBookTypeDesc(book.getBookType().getTypeDesc());
			dto.setBookType(book.getBookType().getId());
			data.add(dto);
		}
		return data;
	}
	
	public BookDTO getBookByISBN(Long isbn)throws Exception{
		Optional<Book> bookHolder = bookRepository.findByIsbn(isbn);		
		BookDTO data = new BookDTO();
		if(bookHolder.isPresent()) {
			Book book = bookHolder.get();
			BeanUtils.copyProperties(book, data);
			data.setBookTypeDesc(book.getBookType().getTypeDesc());
			data.setBookType(book.getBookType().getId());
		}
		return data;
	}
	
	@Override
	public Long save(BookDTO dto) throws Exception {
		Book book = new Book();
		BeanUtils.copyProperties(dto, book);
		book.setBookType(new BookType(dto.getBookType()));
		bookRepository.save(book);
		return book.getId();
	}

	@Override
	public Boolean delete(Long id) throws Exception {
		bookRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public Double checkoutTotal(BookCheckout bookCheckout) throws Exception {
		List<Book> bookList = bookRepository.findByIsbn(bookCheckout.getIsbns());		
		List<Long> bookType = bookList.stream().mapToLong(book -> book.getBookType().getId()).boxed().collect(Collectors.toList());
		List<Discount> discList = discountRepository.findDiscount(bookType);		
		//bookList.stream().filter(discount -> discList.stream().anyMatch(book -> book.getBookType().equals(obj)))
		double total = 0;
		for (Book book : bookList) {
			Double discValue = null;
			Integer discType = 0;//0-No Discount, 1-Per Discount
			for (Discount discount : discList) {				
				if(book.getBookType().getId().equals(discount.getBookType())) {
					discValue = discount.getDiscount();
					discType = discount.getDiscountType();
					break;
				}
			}
			if(discType==0) {
				total += book.getPrice(); 
			}else {
				total += book.getPrice() - (book.getPrice()*discValue);
			}			
		}
		if(StringUtils.hasLength(bookCheckout.getPromoCode())) {
			Optional<Discount> promoDiscountHolder = discountRepository.findByPromoCode(bookCheckout.getPromoCode());
			if(promoDiscountHolder.isPresent()) {
				Discount promoDiscount = promoDiscountHolder.get();
				if(promoDiscount.getDiscountType()==1) {
					total = total - (total*promoDiscount.getDiscount());
				}else if(promoDiscount.getDiscountType()==2) {
					total = total - promoDiscount.getDiscount();
					if(total<0) {
						total = 0;
					}
				}
			}
		}
		return new BigDecimal(total).setScale(2,0).doubleValue();
	}	

}
