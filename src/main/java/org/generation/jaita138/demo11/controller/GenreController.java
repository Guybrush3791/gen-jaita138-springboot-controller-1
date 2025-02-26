package org.generation.jaita138.demo11.controller;

import java.util.List;

import org.generation.jaita138.demo11.db.entity.Genre;
import org.generation.jaita138.demo11.db.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/api/v1/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<Genre>> getGenres() {

        List<Genre> genres = genreService.findAll();
        return ResponseEntity.ok(genres);
    }
}
