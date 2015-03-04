package dk.lessismore.advnojpa.service.impl;

import dk.lessismore.advnojpa.model.Book;
import dk.lessismore.advnojpa.model.Person;
import dk.lessismore.advnojpa.service.BookService;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectSearchService;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectService;
import org.springframework.stereotype.Service;

/**
 * Created by niakoi on 4/3/15.
 */
@Service
public class BookServiceImpl implements BookService {
    @Override
    public Book create(String title) {
        Book book = ModelObjectService.create(Book.class);
        book.setTitle(title);
        ModelObjectService.save(book);
        return book;
    }

    @Override
    public void assign(Book book, Person writer) {
        book.setWriter(writer);
        writer.setBooks(ModelObjectService.addObjectToArray(writer.getBooks(), book));
        writer.setIgnoredBooks(ModelObjectService.addObjectToArray(writer.getBooks(), book));
        save(book, writer);
    }

    @Override
    public void unassign(Book book) {
        Person writer = book.getWriter();
        book.setWriter(null);
        writer.setBooks(ModelObjectService.removeObjectFromArray(writer.getBooks(), book));
        writer.setIgnoredBooks(ModelObjectService.removeObjectFromArray(writer.getBooks(), book));
        save(book, writer);
    }

    private void save(Book book, Person writer) {
        ModelObjectService.save(book);
        ModelObjectService.save(writer);
        ModelObjectSearchService.put(writer);
    }
}
