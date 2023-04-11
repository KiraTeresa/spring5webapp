package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // will register the class as a Spring Bean and as a Controller in Spring MVC
public class BookController {

    private final BookRepository bookRepository; // inject a book repository

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books") // this annotation maps it to a specific URL path
    public String getBooks(Model model){

        model.addAttribute("books", bookRepository.findAll());

        return "books";
    }
}
