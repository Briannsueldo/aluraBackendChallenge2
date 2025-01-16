package com.alura.challengeAlura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alura.challengeAlura.model.BookData;

public interface BookDataRepository extends JpaRepository<BookData,Long> {
    
    @Query(value = """
            SELECT * FROM books
            """, nativeQuery = true)
    List<BookData> findAllBooks();

    @Query(value = """
            SELECT * FROM books
            WHERE LOWER(title) LIKE LOWER(CONCAT('%', :name, '%'))
            """, nativeQuery = true)
    List<BookData> findBookByName(String name);

    @Query(value = """
            SELECT DISTINCT ON (LOWER(title)) *
            FROM books
            WHERE languages @> ARRAY[:language]::varchar[];
            """, nativeQuery = true)
    List<BookData> findByLanguage(String language);
}
