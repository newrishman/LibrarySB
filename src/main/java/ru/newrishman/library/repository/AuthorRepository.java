package ru.newrishman.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.newrishman.library.domain.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
  //  List<Author> findByBooks_bookLikeIgnoreCase(String book);

   // Author findByAuthorIgnoreCase(String Author);
}
