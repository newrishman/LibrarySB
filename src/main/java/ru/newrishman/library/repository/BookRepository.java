package ru.newrishman.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.newrishman.library.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
