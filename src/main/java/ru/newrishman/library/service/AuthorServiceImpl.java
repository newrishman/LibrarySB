package ru.newrishman.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.newrishman.library.domain.Author;
import ru.newrishman.library.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public void setRepository(AuthorRepository repository) {
        this.authorRepository = repository;
    }

    @Override
    public Author addAuthor(Author author) {
        authorRepository.save(author);
        return author;
    }

    @Override
    public void updateAuthor(Author author) {
        Author update = authorRepository.getOne(author.getId());
        update.setAuthor(author.getAuthor());
        authorRepository.save(update);
    }

    @Override
    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author getAuthorById(long id) {
        return authorRepository.getOne(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

   @Override
    public List<Author> findAuthorByBook(String book) {
        return authorRepository.findByBooks_titleLikeIgnoreCase(book);
    }

    @Override
    public Author findAuthorByName(String author) {
        return authorRepository.findByAuthorIgnoreCase(author);
    }
}