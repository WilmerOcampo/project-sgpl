package com.wo.bookservice.service;

import com.wo.bookservice.kafka.BookResponse;
import com.wo.bookservice.model.Book;
import com.wo.bookservice.respository.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final IBookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Book can't be found with id: " + id));
    }

    @Override
    public BookResponse bookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(value -> new BookResponse(value.getId(), value.getTitle())).orElse(null);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book can't be found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findActiveBooks() {
        return bookRepository.findByActiveTrue();
    }

    @Override
    public Book updateActiveStatus(Long id, boolean active) {
        Book book = findById(id);
        book.setActive(active);
        return bookRepository.save(book);
    }
}
