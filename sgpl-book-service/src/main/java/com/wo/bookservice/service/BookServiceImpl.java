package com.wo.bookservice.service;

import com.wo.bookservice.model.Book;
import com.wo.bookservice.respository.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return bookRepository.findByIsActiveTrue();
    }

    @Override
    public Book updateActiveStatus(Long id, boolean isActive) {
        Book book = findById(id);
        book.setActive(isActive);
        return bookRepository.save(book);
    }
}
