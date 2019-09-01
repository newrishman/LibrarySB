package ru.newrishman.library;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.newrishman.library.domain.Book;
import ru.newrishman.library.repository.BookRepository;
import ru.newrishman.library.service.BookService;
import ru.newrishman.library.service.BookServiceImpl;

import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
public class BookServiceImplTest {
    @TestConfiguration
    static class BookServiceImplTestContextConfiguration {

        @Bean
        public BookService bookService() {
            return new BookServiceImpl();
        }
    }

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Before
    public void beforeTest() {
        Book books = new Book(1, "title");
        Mockito.when(bookRepository.getOne(books.getId())).thenReturn(books);
    }

    @Test
    public void getBookByIdTest() {
        long id = 1;
        bookService.getBookById(id);
        Mockito.verify(bookRepository, times(1)).getOne(id);
    }

    @Test
    public void addBookTest() {
        Book bookSave = new Book();
        bookService.addBook(bookSave);
        Mockito.verify(bookRepository, times(1)).save(bookSave);
    }

    @Test
    public void deleteBookTest() {
        long id = 1;
        bookService.deleteBook(id);
        Mockito.verify(bookRepository, times(1)).deleteByIdBook(id);
        Mockito.verify(bookRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllBooksTest() {
        bookService.getAllBooks();
        Mockito.verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void updateBookTest() {
        Book bookUpdate = new Book(1, "title");
        long id = 1;
        bookService.updateBook(bookUpdate);
        Mockito.verify(bookRepository, times(1)).getOne(id);
        Book book = bookRepository.getOne(id);
        Mockito.verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void findBookByAuthor() {
        String authorName = "authorName";
        bookService.findBookByAuthor(authorName);
        Mockito.verify(bookRepository, times(1))
                .findByAuthors_nameLikeIgnoreCase(authorName);
    }

    @Test
    public void findBookByTitle() {
        String title = "bookTitle";
        bookService.findBookByTitle(title);
        Mockito.verify(bookRepository, times(1))
                .findByTitleIgnoreCase(title);
    }
}
