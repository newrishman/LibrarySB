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
import ru.newrishman.library.domain.Author;
import ru.newrishman.library.repository.AuthorRepository;
import ru.newrishman.library.service.AuthorService;
import ru.newrishman.library.service.AuthorServiceImpl;

import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
public class AuthorServiceImplTest {
    @TestConfiguration
    static class AuthorServiceImplTestContextConfiguration {

        @Bean
        public AuthorService authorService() {
            return new AuthorServiceImpl();
        }
    }

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorRepository authorRepository;

    @Before
    public void beforeTest() {
        Author authors = new Author(1, "name");
        Mockito.when(authorRepository.getOne(authors.getId())).thenReturn(authors);
    }

    @Test
    public void getAuthorByIdTest() {
        long id = 1;
        authorService.getAuthorById(id);
        Mockito.verify(authorRepository, times(1)).getOne(id);
    }

    @Test
    public void addAuthorTest() {
        Author authorSave = new Author();
        authorService.addAuthor(authorSave);
        Mockito.verify(authorRepository, times(1)).save(authorSave);
    }

    @Test
    public void deleteAuthorTest() {
        long id = 1;
        authorService.deleteAuthor(id);
        Mockito.verify(authorRepository, times(1)).deleteByIdAuthor(id);
        Mockito.verify(authorRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllAuthorsTest() {
        authorService.getAllAuthors();
        Mockito.verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void updateAuthorTest() {
        Author authorUpdate = new Author(1, "name");
        long id = 1;
        authorService.updateAuthor(authorUpdate);
        Mockito.verify(authorRepository, times(1)).getOne(id);
        Author author = authorRepository.getOne(id);
        Mockito.verify(authorRepository, times(1)).save(author);
    }

    @Test
    public void findAuthorByBook() {
        String bookTitle = "title";
        authorService.findAuthorByBook(bookTitle);
        Mockito.verify(authorRepository, times(1))
                .findByBooks_titleLikeIgnoreCase(bookTitle);
    }

    @Test
    public void findAuthorByName() {
        String name = "name";
        authorService.findAuthorByName(name);
        Mockito.verify(authorRepository, times(1))
                .findByNameIgnoreCase(name);
    }
}
