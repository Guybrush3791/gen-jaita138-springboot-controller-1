package org.generation.jaita138.demo11.db.repo;

import java.util.List;

import org.generation.jaita138.demo11.db.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    // lettura di tutti i libri con titolo che inizia per p (case insensitive)
    List<Book> findByTitleStartingWithIgnoreCase(String prefix);

    // lettura di tutti i libri prodotti tra il 2000 e il 2020
    @Query("SELECT b FROM Book b WHERE b.year_pub > ?1 AND b.year_pub < ?2")
    List<Book> findByYearpubBetween(Integer start, Integer end);

    // trova il libro a partire dall'isbn
    Book findByIsbn(String isbn);
}
