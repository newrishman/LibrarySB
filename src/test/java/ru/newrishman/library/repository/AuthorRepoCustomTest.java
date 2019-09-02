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
public class AuthorRepoCustomTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository repo;

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
    public void findByBooks_titleLikeIgnoreCaseTest() {
        assertEquals(repo.findByBooks_titleLikeIgnoreCase("title").size(), 1);
    }

    @Test
    public void findByNameIgnoreCaseTest() {
        String name = "name";
        assertEquals(repo.findByNameIgnoreCase(name).getName(), name);
    }

    @Test
    public void deleteByIdAuthorTest() {
        repo.deleteByIdAuthor(1L);
        assertEquals(repo.findByBooks_titleLikeIgnoreCase("title").size(), 0);
    }
}
