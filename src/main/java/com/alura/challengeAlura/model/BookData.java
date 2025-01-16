package com.alura.challengeAlura.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookData {
    
    @JsonAlias("title")
    private String title;

    @JsonAlias("authors")
    private List<AuthorData> authors = new ArrayList<>();

    public BookData() {

    }

    public BookData(String title, List<AuthorData> authors) {
        this.title = title;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorData> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorData> authors) {
        this.authors = authors;
    }


    public String formatBookData() {

        String authorsFormatted = authors.stream()
            .map(AuthorData::getName)
            .collect(Collectors.joining("-- "));

        return String.format("""
                Title: %s
                Authors: %s
                """,
                title, authorsFormatted);

    }
}
