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
        //findBookByName("Как терпеть Ришата");
        //findBookByAuthor("Малик");
    }

    @GetMapping("/authors/")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
        //findAuthorByName("Малик");
        //findAuthorByBook("Как терпеть Ришата");
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
    public void add(@RequestBody AuthorBook authorBook) {

        Book book = new Book(authorBook.getBook());
        Set<Book> books = new HashSet<>();
        books.add(book);

        Author author = new Author(authorBook.getAuthor());
        Set<Author> authors = new HashSet<>();
        authors.add(author);

        author.setBooks(books);
        book.setAuthors(authors);

        bookService.addBook(book);
        authorService.addAuthor(author);
    }
}
