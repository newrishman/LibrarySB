package ru.newrishman.library.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.newrishman.library.domain.Author;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthorJpaRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository repo;

    @Test
    public void getAuthorByIdTest() {
        Author author = new Author("name");
        entityManager.persist(author);
        entityManager.flush();

        long id = 1;
        repo.findById(id);

        Author getAuthor = repo.findById(author.getId()).orElse(new Author());
        assertEquals(author.getId(), getAuthor.getId());
    }

    @Test
    public void saveAuthorTest() {
        Author authorSave = new Author("name");
        assertEquals(repo.save(authorSave), authorSave);
    }

    @Test
    public void updateAuthorTest() {
        long id = 1;
        String name = "newName";
        Author author = new Author("name12");
        entityManager.persist(author);
        entityManager.flush();

        Author authorUpdate = repo.findById(id).orElse(new Author());
        authorUpdate.setName(name);
        assertEquals(repo.getOne(id).getName(), name);
    }

    @Test
    public void findAllAuthorsTest() {
        Author author = new Author("name");
        Author author1 = new Author("name1");
        Author author2 = new Author("name2");
        entityManager.persist(author);
        entityManager.persist(author1);
        entityManager.persist(author2);
        entityManager.flush();

        assertEquals(repo.findAll().size(), 3);
    }

    @Test
    public void deleteAuthorTest() {
        Author author = new Author("name");
        entityManager.persist(author);
        entityManager.flush();

        repo.deleteById(author.getId());
        assertTrue(repo.findAll().isEmpty());
        assertFalse(repo.findById(1L).isPresent());
    }
}