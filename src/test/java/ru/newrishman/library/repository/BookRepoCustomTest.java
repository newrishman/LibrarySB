package ru.newrishman.library.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.newrishman.library.domain.Author;
import ru.newrishman.library.domain.Book;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepoCustomTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository repo;

    @Before
    public void createTables() {
        Book book = new Book("title");
        Author author = new Author("name");
        Set<Author> authors = new HashSet<>();
        Set<Book> books = new HashSet<>();
        authors.add(author);
        books.add(book);
        book.setAuthors(authors);
        author.setBooks(books);
        entityManager.persist(book);
        entityManager.persist(author);
        entityManager.flush();
    }

    @Test
    public void findByAuthors_nameLikeIgnoreCaseTest() {
        assertEquals(repo.findByAuthors_nameLikeIgnoreCase("name").size(), 1);
    }

    @Test
    public void findByTitleIgnoreCaseTest() {
        String title = "title";
        assertEquals(repo.findByTitleIgnoreCase(title).getTitle(), title);
    }

    @Test
    public void deleteByIdBook() {
        repo.deleteByIdBook(1L);
        assertEquals(repo.findByAuthors_nameLikeIgnoreCase("name").size(), 0);
    }
}
