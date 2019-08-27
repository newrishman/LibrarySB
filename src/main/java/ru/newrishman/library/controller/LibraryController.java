package ru.newrishman.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.newrishman.library.domain.Author;
import ru.newrishman.library.domain.AuthorBook;
import ru.newrishman.library.domain.Book;
import ru.newrishman.library.service.AuthorService;
import ru.newrishman.library.service.BookService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
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


   @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.getAllBooks());

        return "books";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {
        if (book.getId() == 0) {
            this.bookService.addBook(book);
        } else {
            this.bookService.updateBook(book);
        }

        return "redirect:/books";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id) {
        this.bookService.deleteBook(id);

        return "redirect:/books";
    }

    @RequestMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.getBookById(id));
        model.addAttribute("listBooks", this.bookService.getAllBooks());

        return "books";
    }

    @RequestMapping("bookdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", this.bookService.getBookById(id));

        return "bookdata";
    }

/*   @GetMapping("/books/")
    public Book getAllBooks() {
       return bookService.
                //getAllBooks();
        findBookByTitle("Как терпеть Ришата");
        //findBookByAuthor("Малик");
    }


    @GetMapping("/authors/")
    public List<Author> getAllAuthors() {
        return authorService.
                //getAllAuthors();
        //findAuthorByName("Малик");
        findAuthorByBook("Как терпеть Ришата");
    }*/

/*
    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable long id) {
        return bookService.getBookById(id);
    }

  @GetMapping("/author/{id}")
    public Author getAuthor(@PathVariable long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    public void add(@RequestBody AuthorBook authorBook) {

        Book book = new Book(authorBook.getBook());
        Set<Book> books = new HashSet<>();
        books.add(book);


        Author author =
                authorService.getAuthorById(1); // как будто автор уже есть
        //    new Author(authorBook.getAuthor());  когда новый автор

        author.getBooks().add(book); // в его Set кидаем новую книгу


        // Set<Author> authors = new HashSet<>(); когда новый автор
        // authors.add(author);  когда новый автор

        //author.setBooks(books); когда новый автор
        // book.setAuthors(authors); когда новый автор

        bookService.addBook(book);        // сохраняем новую книгу
        authorService.addAuthor(author);  // перезаписываем автора с сылками на новые книги
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAuthor(@PathVariable long id) {
        authorService.deleteAuthor(id);
    }*/


}
