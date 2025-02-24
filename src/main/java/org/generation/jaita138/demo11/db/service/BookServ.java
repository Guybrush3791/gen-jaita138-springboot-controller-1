package org.generation.jaita138.demo11.db.service;

import java.util.List;

import org.generation.jaita138.demo11.db.entity.Book;
import org.generation.jaita138.demo11.db.repo.BookRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BookServ {

    @Autowired
    private BookRepo bookRepo;

    @Transactional
    public List<Book> findAll(boolean author, boolean genre) {

        List<Book> books = bookRepo.findAll();

        for (Book book : books) {
            if (author)
                Hibernate.initialize(book.getAuthor());

            if (genre)
                Hibernate.initialize(book.getGenres());
        }

        return books;
    }

    public List<Book> findAll() {
        return findAll(false, false);
    }

    public void save(Book book) {

        bookRepo.save(book);
    }

    public List<Book> findAllBooksStartingWith(String prefix) {

        return bookRepo.findByTitleStartingWithIgnoreCase(prefix);
    }

    public List<Book> findAllBooksPublishedBetween(Integer start, Integer end) {

        return bookRepo.findByYearpubBetween(start, end);
    }

    public Book findByIsbn(String isbn) {

        return bookRepo.findByIsbn(isbn);
    }
}
