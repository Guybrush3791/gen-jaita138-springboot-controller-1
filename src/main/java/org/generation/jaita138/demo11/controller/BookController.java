package org.generation.jaita138.demo11.controller;

import java.util.List;

import org.generation.jaita138.demo11.db.entity.Book;
import org.generation.jaita138.demo11.db.service.BookServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookServ bookServ;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {

        List<Book> books = bookServ.findAll();
        return ResponseEntity.ok(books);
    }
}
