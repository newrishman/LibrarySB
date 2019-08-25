package ru.newrishman.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.newrishman.library.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
