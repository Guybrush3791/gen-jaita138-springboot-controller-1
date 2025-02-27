package org.generation.jaita138.demo11.db.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title is required")
    @NotBlank(message = "Title is required")
    @Column(length = 64)
    private String title;

    private Integer year_pub;

    @Column(length = 13)
    private String isbn;

    @ManyToOne
    private Author author;

    @ManyToMany
    private List<Genre> genres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear_pub() {
        return year_pub;
    }

    public void setYear_pub(Integer year_pub) {
        this.year_pub = year_pub;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre genre) {

        if (genres == null)
            genres = new ArrayList<>();

        if (notContainsGenre(genre))
            genres.add(genre);
    }

    public boolean containsGenre(Genre genre) {

        if (genres == null)
            genres = new ArrayList<>();

        for (Genre g : genres)
            if (g.getId().equals(genre.getId()))
                return true;

        return false;
    }

    public boolean notContainsGenre(Genre genre) {

        return !containsGenre(genre);
    }

    @Override
    public int hashCode() {

        return getId().intValue();
    }

    @Override
    public boolean equals(Object obj) {

        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {

        return "Book [\n"
                + "  id=" + id + ",\n"
                + "  title=" + title + ",\n"
                + "  year_pub=" + year_pub + ",\n"
                + "  isbn=" + isbn + "\n"
                + "]";
    }
}
