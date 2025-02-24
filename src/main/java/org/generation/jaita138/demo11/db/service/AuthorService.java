package org.generation.jaita138.demo11.db.service;

import java.util.List;

import org.generation.jaita138.demo11.db.entity.Author;
import org.generation.jaita138.demo11.db.repo.AuthorRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    public List<Author> findAll() {

        return findAll(false);
    }

    @Transactional
    public List<Author> findAll(boolean books) {

        List<Author> authors = authorRepo.findAll();

        if (books) {
            for (Author author : authors) {
                Hibernate.initialize(author.getBooks());
            }
        }

        return authors;
    }

    public Author findById(Long id) {

        return authorRepo.findById(id).orElse(null);
    }

    public void save(Author author) {

        authorRepo.save(author);
    }
}
