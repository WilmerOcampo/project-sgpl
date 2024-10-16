package com.wo.bookservice.controller;

import com.wo.bookservice.model.Book;
import com.wo.bookservice.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@CrossOrigin
public class BookController {

    private final IBookService bookService;

    @GetMapping("/all") //Lista de todos los libros
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}") //Busqueda de un libro
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping("/save") //Guardado de libros
    public ResponseEntity<Book> save(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @PutMapping("/update/{id}") //Actualizacion de libros
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        Book existingBook = bookService.findById(id);
        existingBook.setTitle(book.getTitle());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setNumPages(book.getNumPages());
        existingBook.setCoverImage(book.getCoverImage());
        existingBook.setQuantityAvailable(book.getQuantityAvailable());
        existingBook.setPublicationYear(book.getPublicationYear());
        existingBook.setActive(book.isActive());
        existingBook.setCategoryId(book.getCategoryId());
        return ResponseEntity.ok(bookService.save(existingBook));
    }

    @DeleteMapping("/delete/{id}") //Eliminacion de libros
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/actives") //Lista los libros activos
    public ResponseEntity<List<Book>> findActiveBooks() {
        return ResponseEntity.ok(bookService.findActiveBooks());
    }

    @PutMapping("/updateActiveStatus/{id}") // Actualiza el estado activo/inactivo de un libro
    public ResponseEntity<Book> updateActiveStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(bookService.updateActiveStatus(id, active));
    }

    /*private final KafkaProducer kafkaProducer;
    //private final IClientService clientService;

    @GetMapping("/k/{id}")
    public ResponseEntity<?> findByIdMessage(@PathVariable Long id) throws JsonProcessingException {
        BookResponse book = bookService.bookById(id);

        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.writeValueAsString(book);

        kafkaProducer.sendFindByIdMessage(message);

        return ResponseEntity.ok("Successfully, message send to KafkaClient Producer: " + message);
    }*/
}
