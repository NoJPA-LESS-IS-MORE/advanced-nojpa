package dk.lessismore.advnojpa.helper;

import dk.lessismore.advnojpa.controller.BooksController;
import dk.lessismore.advnojpa.controller.PersonController;
import dk.lessismore.advnojpa.model.Book;
import dk.lessismore.advnojpa.model.Person;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by niakoi on 4/3/15.
 */
public class UrlHelper {
    public static String personUrl(Person person) {
        return linkTo(methodOn(PersonController.class).person(person)).toUri().getPath();
    }

    public static String bookUrl(Book book) {
        return linkTo(methodOn(BooksController.class).book(book)).toUri().getPath();
    }
}
