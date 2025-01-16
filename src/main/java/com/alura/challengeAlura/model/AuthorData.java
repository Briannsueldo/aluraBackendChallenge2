package com.alura.challengeAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    
    @JsonAlias("name")
    private String name;

    @JsonAlias("birth_year")
    private Integer bYear;

    @JsonAlias("death_year")
    private Integer dYear;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookData book;

    public AuthorData() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getbYear() {
        return bYear;
    }

    public void setbYear(Integer bYear) {
        this.bYear = bYear;
    }

    public Integer getdYear() {
        return dYear;
    }

    public void setdYear(Integer dYear) {
        this.dYear = dYear;
    }

    public BookData getBook() {
        return book;
    }

    public void setBook(BookData book) {
        this.book = book;
    }

    public String formatAuthorData() {

        return String.format("""
            Name: %s
            Birth year: %d
            Death year: %d
            """,
            name, bYear, dYear);
    }
}
