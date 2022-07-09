package com.tt.bookstore;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tt.bookstore.dto.BookCheckout;
import com.tt.bookstore.dto.BookDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	@DisplayName("/book/add rest api test")
	void addTest() {
		URI targetUrl = UriComponentsBuilder.fromUriString("/book/add").build().encode().toUri();
		Long isbn=1188l;
		BookDTO bookDTO = new BookDTO(null, "Book-"+isbn, "Book Desc-"+isbn, "Book Author-"+isbn, 3l, 30.5, isbn);		
		Long id = this.testRestTemplate.postForObject(targetUrl,getHttpRequest(bookDTO),Long.class);
		assert (id > 0);
	}
	
	@Test
	@DisplayName("/book/update rest api test")
	void updateTest() {
		URI targetUrl = UriComponentsBuilder.fromUriString("/book/update").build().encode().toUri();
		BookDTO bookDTO = new BookDTO(1l, "Book-1", "Book-1 Desc Update", "Book-1 Author", 1l, 21.5, 1122l);		
		Boolean status = this.testRestTemplate.postForObject(targetUrl,getHttpRequest(bookDTO),Boolean.class);
		assert(status);
	}
	
	@Test
	@DisplayName("/book/delete/{id} rest api test")
	void deleteTest() {
		Long id=137l;
		URI targetUrl = UriComponentsBuilder.fromUriString("/book/delete/"+id).build().encode().toUri();
		Boolean status = this.testRestTemplate.postForObject(targetUrl,getHttpRequest(null),Boolean.class);
		assert(status);
	}
	
	@Test
	@DisplayName("/book/list rest api test")
	void listTest() {
		URI targetUrl = UriComponentsBuilder.fromUriString("/book/list").build().encode().toUri();
		List<BookDTO> list = this.testRestTemplate.getForObject(targetUrl, List.class);
		assert (list.size() > 0);
	}
	
	@Test
	@DisplayName("/book/getBook/{isbn} rest api test")
	void getBookTest() {
		Long isbn=1122l;
		URI targetUrl = UriComponentsBuilder.fromUriString("/book/getBook/"+isbn).build().encode().toUri();
		BookDTO obj = this.testRestTemplate.getForObject(targetUrl, BookDTO.class);
		assert (obj!=null && obj.getId()>0);
	}
	
	@Test
	@DisplayName("/book/checkoutTotal rest api test")
	void checkoutTotalTest() {
		URI targetUrl = UriComponentsBuilder.fromUriString("/book/checkoutTotal").build().encode().toUri();
		List<Long> isbns=Arrays.asList(1122l,1133l,1144l);
		String promoCode=null;
		BookCheckout checkout = new BookCheckout(isbns, promoCode);		
		Double total = this.testRestTemplate.postForObject(targetUrl,getHttpRequest(checkout),Double.class);
		assert (total!=null);
	}
	
	private <T> HttpEntity<String> getHttpRequest(T dto) {
		String requestJSON = null;
		if(dto!=null) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			requestJSON = gson.toJson(dto);
		}
		HttpHeaders headers = new HttpHeaders();
		MediaType mType = new MediaType("application", "json", Charset.forName("UTF-8"));
		headers.setContentType(mType);
		return new HttpEntity<String>(requestJSON,headers);
	}
}
