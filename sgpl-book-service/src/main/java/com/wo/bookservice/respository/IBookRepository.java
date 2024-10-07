package com.wo.bookservice.respository;

import com.wo.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {
}
