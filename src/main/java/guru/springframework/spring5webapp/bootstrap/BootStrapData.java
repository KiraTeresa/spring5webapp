package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner; // Interface used to indicate that a bean should run when it is contained within a SpringApplication
import org.springframework.stereotype.Component;

@Component // makes the spring framework to detect this as a spring managed component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    // Once Spring implements this component, it brings it into the spring context
    // Tells Spring to inject an instance of the authorRepository and the bookRepository
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        // using the repository methods to save them
        // Underneath the covers, Spring Data JPA is utilizing Hibernate to save these into the inmemory H2 db
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "johnson");
        Book noEJB = new Book("J2EE Development without EJB", "987987");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
    }
}
