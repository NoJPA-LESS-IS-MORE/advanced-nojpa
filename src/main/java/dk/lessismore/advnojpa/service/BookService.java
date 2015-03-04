package dk.lessismore.advnojpa.service;

import dk.lessismore.advnojpa.model.Book;
import dk.lessismore.advnojpa.model.Person;

/**
 * Created by niakoi on 4/3/15.
 */
public interface BookService {
    Book create(String title);
    void assign(Book book, Person writer);
    void unassign(Book book);
}
