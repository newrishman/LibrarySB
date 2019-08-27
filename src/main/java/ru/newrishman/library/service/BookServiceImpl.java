package ru.newrishman.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.newrishman.library.domain.Book;
import ru.newrishman.library.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setRepository(BookRepository repository) {
        this.bookRepository = repository;
    }

    @Override
    public Book addBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    public void updateBook(Book book) {
        Book update = bookRepository.getOne(book.getId());
        update.setTitle(book.getTitle());
        bookRepository.save(update);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteByIdBook(id);
        bookRepository.deleteById(id);
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.getOne(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthors_authorLikeIgnoreCase(author);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }
}