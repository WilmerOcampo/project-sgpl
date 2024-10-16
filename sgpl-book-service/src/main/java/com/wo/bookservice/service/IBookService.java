package com.wo.bookservice.service;

import com.wo.bookservice.kafka.BookResponse;
import com.wo.bookservice.model.Book;

import java.util.List;

public interface IBookService {

    List<Book> findAll();

    Book findById(Long id);

    BookResponse bookById(Long id);

    Book save(Book book);

    void deleteById(Long id);

    List<Book> findActiveBooks();

    Book updateActiveStatus(Long id, boolean active);
}
