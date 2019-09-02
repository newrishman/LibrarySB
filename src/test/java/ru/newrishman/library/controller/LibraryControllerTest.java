package ru.newrishman.library.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.newrishman.library.service.AuthorService;
import ru.newrishman.library.service.BookService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LibraryController.class)
public class LibraryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @Test
    public void homePageTest() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"));
    }

    @Test
    public void listBooksTest() throws Exception {
        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/books.jsp"));
    }

    @Test
    public void listAuthorsTest() throws Exception {
        mvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/authors.jsp"));
    }

    @Test
    public void listBooksByAuthorTest() throws Exception {
        mvc.perform(get("/books/{id}"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/books.jsp"));
    }

    @Test
    public void listAuthorsByBookTest() throws Exception {
        mvc.perform(get("/authors/{id}"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"));
    }

    @Test
    public void addBookTest() throws Exception {
        mvc.perform(post("/books/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/books"));
    }

    @Test
    public void saveBookTest() throws Exception {
        mvc.perform(post("/books/upload"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/books"));
    }

    @Test
    public void addAuthorTest() throws Exception {
        mvc.perform(post("/authors/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/authors"));
    }

    @Test
    public void deleteBookTest() throws Exception {
        mvc.perform(get("/books/delete/{id}"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/books"));
    }

    @Test
    public void deleteAuthorsTest() throws Exception {
        mvc.perform(get("/authors/delete/{id}"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/authors"));
    }

    @Test
    public void editBookTest() throws Exception {
        mvc.perform(get("books/edit/{id}"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/books.jsp"));
    }

    @Test
    public void editAuthorTest() throws Exception {
        mvc.perform(get("authors/edit/{id}"))
                .andExpect(status().isOk())
                .andExpect(view().name("authors"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/authors.jsp"));
    }

    @Test
    public void downloadTextTest() throws Exception {
        mvc.perform(get("/download/{id}"))
                .andExpect(status().isOk());
    }
}
