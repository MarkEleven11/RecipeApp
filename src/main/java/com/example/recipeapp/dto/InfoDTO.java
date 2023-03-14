package com.example.recipeapp.dto;

import java.time.LocalDate;

public class InfoDTO {


    private final String author;

    private final String title;

    private final LocalDate creationDate;

    private final String description;

    public InfoDTO(String author, String title, LocalDate creationDate, String description) {
        this.author = author;
        this.title = title;
        this.creationDate = creationDate;
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getDescription() {
        return description;
    }
}
