package dk.lessismore.advnojpa.service.impl;

import dk.lessismore.advnojpa.model.Book;
import dk.lessismore.advnojpa.model.Person;
import dk.lessismore.advnojpa.service.BookService;
import dk.lessismore.advnojpa.service.PersonService;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectSearchService;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by niakoi on 4/3/15.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private PersonService personService;

    @Override
    public Book create(String title) {
        Book book = ModelObjectService.create(Book.class);
        book.setTitle(title);
        ModelObjectService.save(book);
        return book;
    }

    @Override
    public void assign(Book book, Person writer) {
        if (book.getWriter() != null) {
           personService.removeBook(book.getWriter(), book);
        }
        book.setWriter(writer);
        personService.addBook(writer, book);
        save(book);
    }

    private void save(Book book) {
        ModelObjectService.save(book);
    }
}
