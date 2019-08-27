package ru.newrishman.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.newrishman.library.domain.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByBooks_titleLikeIgnoreCase(String book);

    Author findByNameIgnoreCase(String Author);

    @Modifying
    @Transactional
    @Query(value = "delete from author_book where id_author = :id", nativeQuery = true)
    void deleteByIdAuthor(@Param("id") long id);
}