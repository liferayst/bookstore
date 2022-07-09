package com.tt.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tt.bookstore.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	@EntityGraph(type=EntityGraphType.FETCH,attributePaths = "bookType")
	List<Book> findAll();
	
	@EntityGraph(type=EntityGraphType.FETCH,attributePaths = "bookType")
	Optional<Book> findByIsbn(Long isbn);
	
	@Query("from Book where isbn in (?1)")
	List<Book> findByIsbn(List<Long> isbn);
	
}