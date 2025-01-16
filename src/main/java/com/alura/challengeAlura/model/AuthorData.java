package com.alura.challengeAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorData {
    
    @JsonAlias("name")
    private String name;

    @JsonAlias("birth_year")
    private Integer bYear;

    @JsonAlias("death_year")
    private Integer dYear;

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

    public String formatAuthorData() {

        return String.format("""
            Name: %s
            Birth year: %d
            Death year: %d
            """,
            name, bYear, dYear);
    }
}
