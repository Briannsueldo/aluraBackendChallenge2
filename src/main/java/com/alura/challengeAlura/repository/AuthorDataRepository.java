package com.alura.challengeAlura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alura.challengeAlura.model.AuthorData;

public interface AuthorDataRepository extends JpaRepository<AuthorData,Long> {
    
    AuthorData findByName(String name);

    @Query(value = """
            SELECT DISTINCT ON (LOWER(name)) *
            FROM authors
            """,nativeQuery = true)
    List<AuthorData> findAuthorName();

    @Query(value = """
            SELECT DISTINCT ON (LOWER(name)) *
            FROM authors
            WHERE b_year <= :yearSelected AND d_year >= :yearSelected
            """, nativeQuery = true)
    List<AuthorData> findAuthorsByYear(Integer yearSelected);
}