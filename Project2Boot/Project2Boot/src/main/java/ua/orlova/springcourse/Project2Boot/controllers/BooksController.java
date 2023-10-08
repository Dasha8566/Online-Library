package ua.orlova.springcourse.Project2Boot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.orlova.springcourse.Project2Boot.dao.BookDAO;
import ua.orlova.springcourse.Project2Boot.models.Book;
import ua.orlova.springcourse.Project2Boot.models.Person;
import ua.orlova.springcourse.Project2Boot.services.BooksService;
import ua.orlova.springcourse.Project2Boot.services.PeopleService;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BookDAO bookDAO, BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;

    }


    @GetMapping()
    public String index(Model model, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> books_per_page, @RequestParam Optional<Boolean> sort_by_year) {
        if (page.isPresent() && books_per_page.isPresent()) {
            model.addAttribute("books", booksService.findAll(page.get(), books_per_page.get()));
        } else if (page.isPresent() && books_per_page.isPresent() && sort_by_year.isPresent()) {
            model.addAttribute("books", booksService.findAll(page.get(), books_per_page.get(), sort_by_year.get()));
        } else if (sort_by_year.isPresent()) {
            model.addAttribute("books", booksService.findAll(sort_by_year.get()));
        } else {
            model.addAttribute("books", booksService.findAll());
            model.addAttribute("books", booksService.findAll());
        }
        return "books/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findOne(id));
        Optional<Person> bookOwner = booksService.getBookOwner(id);
        if (bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        } else {
            model.addAttribute("people", peopleService.findAll());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }

    @PostMapping("{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        booksService.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        booksService.assign(id, selectedPerson);
        return "redirect:/books/" + id;
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam Optional<String> s) {
        if (s.isPresent()) {
            Optional<Book> foundBook = booksService.search(s.get());
            if (foundBook.isPresent()) {
                model.addAttribute("book", foundBook.get());
                Optional<Person> bookOwner = booksService.getBookOwner(foundBook.get().getId());
                if (bookOwner.isPresent()) {
                    model.addAttribute("owner", bookOwner.get());
                } else {
                    model.addAttribute("people", peopleService.findAll());
                }
            }else{
                model.addAttribute("noBooks", 5);
            }

        }
        return "books/search";
    }
}
