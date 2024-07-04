package com.packt.booklibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.booklibrary.model.Book;
import com.packt.booklibrary.model.BookRepository;

@RestController
public class BookController {
    @Autowired
    private BookRepository repository;

    @RequestMapping("/books")
    public Iterable<Book> getBooks() {
        return repository.findAll();
    }
}
