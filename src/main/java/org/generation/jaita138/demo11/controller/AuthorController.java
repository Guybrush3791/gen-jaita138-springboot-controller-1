package org.generation.jaita138.demo11.controller;

import java.util.List;

import org.generation.jaita138.demo11.db.entity.Author;
import org.generation.jaita138.demo11.db.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> getAuthors() {

        List<Author> authors = authorService.findAll();
        return ResponseEntity.ok(authors);
    }
}
