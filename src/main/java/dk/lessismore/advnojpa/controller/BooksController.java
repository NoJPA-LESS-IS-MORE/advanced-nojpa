package dk.lessismore.advnojpa.controller;

import dk.lessismore.advnojpa.helper.UrlHelper;
import dk.lessismore.advnojpa.model.Book;
import dk.lessismore.advnojpa.model.Person;
import dk.lessismore.advnojpa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by niakoi on 4/3/15.
 */
@RestController
public class BooksController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/people/{person}/books")
    public Book[] books(@PathVariable Person person) {
        return person.getBooks();
    }


    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public View create(@RequestParam String title) {
        Book book = bookService.create(title);
        return new RedirectView(UrlHelper.bookUrl(book));
    }


    @RequestMapping(value = "/people/{person}/books", method = RequestMethod.POST)
    public Book[] assignBook(@PathVariable Person person, @RequestParam Book book) {
        bookService.assign(book, person);
        return person.getBooks();
    }

    @RequestMapping("/books/{book}")
    public Book book(@PathVariable Book book) {
        return book;
    }

}
