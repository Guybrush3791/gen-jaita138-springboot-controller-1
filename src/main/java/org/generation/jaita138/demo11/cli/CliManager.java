package org.generation.jaita138.demo11.cli;

import java.util.List;
import java.util.Scanner;

import org.generation.jaita138.demo11.db.entity.Author;
import org.generation.jaita138.demo11.db.entity.Book;
import org.generation.jaita138.demo11.db.entity.Genre;
import org.generation.jaita138.demo11.db.service.AuthorService;
import org.generation.jaita138.demo11.db.service.BookServ;
import org.generation.jaita138.demo11.db.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;

public class CliManager {

    private Scanner scanner;

    private BookServ bookServ;
    private AuthorService authorServ;
    private GenreService genreServ;

    public CliManager(
            BookServ bookServ,
            AuthorService authorServ,
            GenreService genreServ) {

        this.bookServ = bookServ;
        this.authorServ = authorServ;
        this.genreServ = genreServ;

        scanner = new Scanner(System.in);

        printMenu();
    }

    private void printMenu() {

        System.out.println("READING");
        System.out.println("1. List all books");
        System.out.println("2. List all books w/ author");
        System.out.println("3. List all books w/ author and genre");
        System.out.println("4. List all authors");
        System.out.println("5. List all genres");
        printSeparetor();

        System.out.println("WRITING");
        System.out.println("6. Add author");
        System.out.println("7. Add genre");
        System.out.println("8. Add book");
        printSeparetor();

        System.out.println("QUERING");
        System.out.println("9. Find all books with title starting with 'p' (case insensitive)");
        System.out.println("10. Find all books published after 2000 and before 2020");
        System.out.println("11. Find the book with ISBN equals to '978-3-16-148410-0'");
        printSeparetor();

        System.out.println("BONUS");
        System.out.println("12. List all authors w/ books");
        System.out.println("13. List all genres w/ books");

        System.out.println("-1. Exit");
        printSeparetor();

        String userValue = scanner.nextLine();
        Integer userChoise = Integer.valueOf(userValue);

        switch (userChoise) {

            case 1:
                listAllBooks();
                break;

            case 2:
                listAllBooks(true, false);
                break;

            case 3:
                listAllBooks(true, true);
                break;

            case 4:
                listAllAuthors();
                break;

            case 5:
                listAllGenre();
                break;

            case 6:
                addAuthor();
                break;

            case 7:
                addGenre();
                break;

            case 8:
                addBook();
                break;

            case 9:
                listAllBooksStartingWith("p");
                break;

            case 10:
                listAllBooksProdBetween(2000, 2020);
                break;

            case 11:
                printBookByIsbn("978-3-16-148410-0");
                break;

            case 12:
                listAllAuthorsWBooks();
                break;

            case 13:
                listAllGenresWBooks();
                break;

            case -1:
                System.out.println("Goodbye!");
                return;

            default:
                System.out.println("Invalid option");
        }

        printMenu();
    }

    private void listAllBooks() {
        listAllBooks(false, false);
    }

    private void listAllBooks(boolean author, boolean genre) {
        List<Book> books = bookServ.findAll(author, genre);

        System.out.println("Books:");
        for (Book book : books) {
            System.out.println(book);

            if (author)
                System.out.println("  Author: " + book.getAuthor());

            if (genre)
                System.out.println("  Genres: " + book.getGenres());
        }

        printSeparetor();
    }

    private void listAllAuthors() {

        List<Author> authors = authorServ.findAll();

        System.out.println("Authors:");
        for (Author author : authors) {
            System.out.println(author);
        }

        printSeparetor();
    }

    private void listAllGenre() {

        List<Genre> genres = genreServ.findAll();

        System.out.println("Genres:");
        for (Genre genre : genres) {
            System.out.println(genre);
        }

        printSeparetor();
    }

    private void addAuthor() {

        Author author = new Author();

        System.out.println("Name:");
        String name = scanner.nextLine();
        author.setName(name);

        System.out.println("Surname:");
        String surname = scanner.nextLine();
        author.setSurname(surname);

        System.out.println("Nationality (es: US, IT):");
        String nationality = scanner.nextLine();
        author.setNationality(nationality);

        authorServ.save(author);
    }

    private void addGenre() {

        Genre genre = new Genre();

        System.out.println("Name:");
        String name = scanner.nextLine();
        genre.setName(name);

        genreServ.save(genre);
    }

    private void addBook() {

        Book book = new Book();

        System.out.println("Title:");
        String title = scanner.nextLine();
        book.setTitle(title);

        System.out.println("Year of publication:");
        String year_pub = scanner.nextLine();
        book.setYear_pub(Integer.valueOf(year_pub));

        System.out.println("ISBN:");
        String isbn = scanner.nextLine();
        book.setIsbn(isbn);

        List<Author> authors = authorServ.findAll();
        System.out.println("Authors:");
        authors
                .stream()
                .map(a -> a.getId() + ". " + a.getName() + " " + a.getSurname())
                .forEach(System.out::println);

        System.out.println("Author id: ");
        String authorIdStr = scanner.nextLine();
        Long authorId = Long.valueOf(authorIdStr);
        Author author = authorServ.findById(authorId);
        book.setAuthor(author);

        List<Genre> genres = genreServ.findAll();
        while (true) {

            System.err.println("Genres:");
            genres
                    .stream()
                    .filter(g -> book.notContainsGenre(g))
                    .map(g -> g.getId() + ". " + g.getName())
                    .forEach(System.out::println);

            System.out.println("Genre id: ");
            String genreIdStr = scanner.nextLine();
            Long genreId = Long.valueOf(genreIdStr);
            Genre genre = genreServ.findById(genreId);

            book.addGenre(genre);

            System.out.println("Add another genre? (y/n)");
            String addAnother = scanner.nextLine();
            if (!addAnother.equals("y"))
                break;
        }

        bookServ.save(book);
    }

    private void listAllBooksStartingWith(String prefix) {

        List<Book> books = bookServ.findAllBooksStartingWith(prefix);

        System.out.println("Books starting with '" + prefix + "':");
        System.out.println(books);
    }

    private void listAllBooksProdBetween(Integer startYear, Integer endYear) {

        List<Book> books = bookServ.findAllBooksPublishedBetween(startYear, endYear);

        System.out.println("Books published between " + startYear + " and " + endYear + ":");
        System.out.println(books);
    }

    private void printBookByIsbn(String isbn) {

        String cleanIsbn = isbn.replaceAll("-", "");

        Book book = bookServ.findByIsbn(cleanIsbn);

        System.out.println("Book with ISBN '" + isbn + "':");
        System.out.println(book);
    }

    private void listAllAuthorsWBooks() {

        List<Author> authors = authorServ.findAll(true);

        System.out.println("Authors:");
        for (Author author : authors) {
            System.out.println(author);
            System.out.println("  Books: " + author.getBooks());
        }

        printSeparetor();
    }

    private void listAllGenresWBooks() {

        List<Genre> genres = genreServ.findAll(true);

        System.out.println("Genres:");
        for (Genre genre : genres) {
            System.out.println(genre);
            System.out.println("  Books: " + genre.getBooks());
        }

        printSeparetor();
    }

    private void printSeparetor() {
        System.out.println("====================================");
    }
}
