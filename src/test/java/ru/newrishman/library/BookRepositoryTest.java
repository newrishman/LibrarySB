package ru.newrishman.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.newrishman.library.domain.Book;
import ru.newrishman.library.repository.BookRepository;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository repo;

    @Test
    public void getBookByIdTest() {
        Book book = new Book("book");
        entityManager.persist(book);
        entityManager.flush();

        long id = 1;
        repo.findById(id);

        Book getBook = repo.findById(book.getId()).orElse(new Book());
        assertEquals(book.getId(), getBook.getId());
    }

    @Test
    public void saveBookTest() {
        Book bookSave = new Book("book");
        assertEquals(repo.save(bookSave), bookSave);
    }

    @Test
    public void updateBookTest() {
        long id = 1;
        String title = "newTitle";
        Book book = new Book("title");
        entityManager.persist(book);
        entityManager.flush();

        Book bookUpdate = repo.findById(id).orElse(new Book());
        bookUpdate.setTitle(title);
        assertEquals(repo.getOne(id).getTitle(), title);
    }

    @Test
    public void findAllBooksTest() {
        Book book = new Book("title1");
        Book book2 = new Book("title2");
        Book book3 = new Book("title3");
        entityManager.persist(book);
        entityManager.persist(book2);
        entityManager.persist(book3);
        entityManager.flush();

        assertEquals(repo.findAll().size(), 3);
    }

    @Test
    public void deleteBookTest() {
        Book book = new Book("title");
        entityManager.persist(book);
        entityManager.flush();

        repo.deleteById(book.getId());
        assertTrue(repo.findAll().isEmpty());
        assertFalse(repo.findById(1L).isPresent());
    }
}