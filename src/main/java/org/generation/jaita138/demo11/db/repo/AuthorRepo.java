package org.generation.jaita138.demo11.db.repo;

import org.generation.jaita138.demo11.db.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

}
