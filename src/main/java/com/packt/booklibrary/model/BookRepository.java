package com.packt.booklibrary.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, Long>  {
	// Fetch books by Book ID
	List<Book> findByBookId(@Param("bookId") int bookId);
	
	// Fetch books by Title
	List<Book> findByTitle(@Param("title") String title);
	
	// Fetch books by ISBN
	List<Book> findByIsbn(@Param("isbn") int isbn);
}
