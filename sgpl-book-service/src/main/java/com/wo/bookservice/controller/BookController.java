package com.wo.bookservice.controller;

import com.wo.bookservice.model.Book;
import com.wo.bookservice.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@CrossOrigin
public class BookController {

    private final IBookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

}
