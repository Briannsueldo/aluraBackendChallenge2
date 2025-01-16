package com.alura.challengeAlura.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    
    @Column(unique = true)
    @JsonAlias("title")
    private String title;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonAlias("authors")
    private List<AuthorData> authors = new ArrayList<>();

    @JsonAlias("download_count")
    private Integer downloads;

    @JsonAlias("languages")
    private List<String> languages = new ArrayList<>();

    @Column(name = "author_name")
    private String authorName;
    
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authors.get(0).getName();
    }

    public BookData() {

    }

    public BookData(String title, List<AuthorData> authors, Integer downloads, List<String> languages) {
        this.title = title;
        this.authors = authors;
        this.downloads = downloads;
        this.languages = languages;
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

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }


    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String formatBookData() {

        String authorsFormatted = authors.stream()
            .map(AuthorData::getName)
            .collect(Collectors.joining("-- "));

        return String.format("""
                Titulo: %s
                Autores: %s
                Descargas: %d
                Lenguajes: %s
                """,
                title, authorsFormatted, downloads, String.join(", ", languages));

    }
}
