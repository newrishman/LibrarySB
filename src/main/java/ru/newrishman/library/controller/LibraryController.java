package ru.newrishman.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.newrishman.library.domain.Author;
import ru.newrishman.library.domain.Book;
import ru.newrishman.library.service.AuthorService;
import ru.newrishman.library.service.BookService;

import java.io.IOException;
import java.util.HashSet;
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

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", bookService.getAllBooks());
        return "books";
    }

    @RequestMapping(value = "authors", method = RequestMethod.GET)
    public String listAuthors(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("listAuthors", authorService.getAllAuthors());
        return "authors";
    }

    @RequestMapping(value = "books/{id}", method = RequestMethod.GET)
    public String listBooksByAuthor(@PathVariable("id") long id, Model model) {
        Author author = authorService.getAuthorById(id);

        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", bookService.findBookByAuthor(author.getName()));
        return "books";
    }

    @RequestMapping(value = "authors/{id}", method = RequestMethod.GET)
    public String listAuthorsByBook(@PathVariable("id") long id, Model model) {
        Book book = bookService.getBookById(id);

        model.addAttribute("author", new Author());
        model.addAttribute("listAuthors", authorService.findAuthorByBook(book.getTitle()));
        return "authors";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {
        if (book.getId() == 0) {
            bookService.addBook(book);
        } else {
            bookService.updateBook(book);
        }
        return "redirect:/books";
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String saveBook(@RequestParam("name") String authorName,
                           @RequestParam("file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();

        Book book = new Book(file.getOriginalFilename(), bytes);
        Set<Book> books = new HashSet<>();
        books.add(book);

        Author author;
        Author search = authorService.findAuthorByName(authorName);
        if (search == null) {
            author = new Author(authorName);

            Set<Author> authors = new HashSet<>();
            authors.add(author);

            author.setBooks(books);
            book.setAuthors(authors);
        } else {
            author = search;
            author.getBooks().add(book);
        }
        bookService.addBook(book);
        authorService.addAuthor(author);

        return "redirect:/books";
    }

    @RequestMapping(value = "/authors/add", method = RequestMethod.POST)
    public String addAuthor(@ModelAttribute("author") Author author) {
        if (author.getId() == 0) {
            authorService.addAuthor(author);
        } else {
            authorService.updateAuthor(author);
        }
        return "redirect:/authors";
    }

    @RequestMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @RequestMapping("/authors/delete/{id}")
    public String deleteAuthors(@PathVariable("id") long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }

    @RequestMapping("books/edit/{id}")
    public String editBook(@PathVariable("id") long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("listBooks", bookService.getAllBooks());
        return "books";
    }

    @RequestMapping("authors/edit/{id}")
    public String editAuthor(@PathVariable("id") long id, Model model) {
        model.addAttribute("author", authorService.getAuthorById(id));
        model.addAttribute("listAuthors", authorService.getAllAuthors());
        return "authors";
    }

/*   @GetMapping("/books/")
    public Book getAllBooks() {
       return bookService.
                //getAllBooks();
        findBookByTitle("Как терпеть Ришата");
        //findBookByAuthor("Малик");
    }

 <%--  <td><a href="/bookdata/${book.id}" target="_blank">${book.book}</a></td>--%>

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
