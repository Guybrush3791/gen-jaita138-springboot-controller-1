package org.generation.jaita138.demo11.db.service;

import java.util.List;

import org.generation.jaita138.demo11.db.entity.Genre;
import org.generation.jaita138.demo11.db.repo.GenreRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class GenreService {

    @Autowired
    private GenreRepo genreRepo;

    public List<Genre> findAll() {

        return findAll(false);
    }

    @Transactional
    public List<Genre> findAll(boolean books) {

        List<Genre> genres = genreRepo.findAll();

        if (books) {
            for (Genre genre : genres) {
                Hibernate.initialize(genre.getBooks());
            }
        }

        return genres;
    }

    public Genre findById(Long id) {

        return genreRepo.findById(id).orElse(null);
    }

    public void save(Genre genre) {

        genreRepo.save(genre);
    }
}
