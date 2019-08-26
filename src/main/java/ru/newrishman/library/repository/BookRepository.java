package ru.newrishman.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.newrishman.library.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthors_authorLikeIgnoreCase(String author);
}
