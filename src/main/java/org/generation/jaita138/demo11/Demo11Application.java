package org.generation.jaita138.demo11;

import java.util.List;

import org.generation.jaita138.demo11.cli.CliManager;
import org.generation.jaita138.demo11.db.entity.Author;
import org.generation.jaita138.demo11.db.entity.Book;
import org.generation.jaita138.demo11.db.entity.Genre;
import org.generation.jaita138.demo11.db.service.AuthorService;
import org.generation.jaita138.demo11.db.service.BookServ;
import org.generation.jaita138.demo11.db.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo11Application implements CommandLineRunner {

	@Autowired
	private BookServ bookServ;

	@Autowired
	private AuthorService authorServ;

	@Autowired
	private GenreService genreServ;

	public static void main(String[] args) {
		SpringApplication.run(Demo11Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// testBook();
		// testAuthor();
		// testGenre();

		new CliManager(bookServ, authorServ, genreServ);
	}

	public void testBook() {

		Book book = new Book();
		book.setTitle("The Great Gatsby");
		book.setYear_pub(1925);
		book.setIsbn("9780743273565");
		bookServ.save(book);

		book = new Book();
		book.setTitle("To Kill a Mockingbird");
		book.setYear_pub(1960);
		book.setIsbn("9780061120084");
		bookServ.save(book);

		List<Book> books = bookServ.findAll();
		System.out.println(books);
	}

	public void testAuthor() {

		Author author = new Author();
		author.setName("F. Scott Fitzgerald");
		author.setSurname("Fitzgerald");
		author.setNationality("US");
		authorServ.save(author);

		author = new Author();
		author.setName("Harper");
		author.setSurname("Lee");
		author.setNationality("ES");
		authorServ.save(author);

		List<Author> authors = authorServ.findAll();
		System.out.println(authors);
	}

	public void testGenre() {

		Genre genre = new Genre();
		genre.setName("Fiction");
		genreServ.save(genre);

		genre = new Genre();
		genre.setName("Non-fiction");
		genreServ.save(genre);

		List<Genre> genres = genreServ.findAll();
		System.out.println(genres);
	}
}
