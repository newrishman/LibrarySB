package ru.newrishman.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.newrishman.library.domain.Author;
import ru.newrishman.library.domain.AuthorBook;
import ru.newrishman.library.domain.Book;
import ru.newrishman.library.service.AuthorService;
import ru.newrishman.library.service.BookService;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/library")
public class LibraryController {
    private AuthorService authorService;
    private BookService bookService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/authors/")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/author/{id}")
    public Author getAuthor(@PathVariable long id) {
        return authorService.getAuthorById(id);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable long id) {
        authorService.deleteAuthor(id);
    }

    @PostMapping
    public Long add(@RequestBody AuthorBook authorBook){
        Book book = new Book();
        book.setBook(authorBook.getBook());
        Author author= new Author();
        author.setAuthor(authorBook.getAuthor());

        Set<Author> authors = new HashSet<>();
        authors.add(author);
        Set<Book> books = new HashSet<>();
        books.add(book);

        bookService.addBook(book);
        authorService.addAuthor(author);
        return 1L;
    }
  /*  @PutMapping
    public void update(@RequestBody Book book) {
        bookService.updateBook(book);
    }

    @PostMapping
    public Long add(@RequestBody Book book) {
        return bookService.addBook(book).getId();
    }*/
}
