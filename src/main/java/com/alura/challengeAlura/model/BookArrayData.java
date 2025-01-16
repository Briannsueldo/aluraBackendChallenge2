package com.alura.challengeAlura.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class BookArrayData {

    @JsonAlias("results")
    private List<BookData> books = new ArrayList<>();

    public BookArrayData() {
    }

    public BookArrayData(List<BookData> books) {
        this.books = books;
    }

    public List<BookData> getBooks() {
        return books;
    }

    public void setBooks(List<BookData> books) {
        this.books = books;
    }
}
