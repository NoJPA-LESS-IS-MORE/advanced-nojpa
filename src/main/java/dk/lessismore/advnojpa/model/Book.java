package dk.lessismore.advnojpa.model;

import dk.lessismore.nojpa.db.methodquery.MQL;
import dk.lessismore.nojpa.reflection.db.annotations.SearchField;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectInterface;
import dk.lessismore.nojpa.rest.Locator;
import dk.lessismore.nojpa.rest.ObjectLocator;

/**
 * Created by niakoi on 4/3/15.
 */
@Locator(locator = Book.Locator.class)
public interface Book extends ModelObjectInterface {

    @SearchField
    String getTitle();
    void setTitle(String title);

    @SearchField
    Person getWriter();
    void setWriter(Person writer);

    public static class Locator implements ObjectLocator<Book> {
        @Override
        public Book get(String url) {
            Book mBook = MQL.mock(Book.class);
            Book book = MQL.select(mBook).where(mBook.getTitle(), MQL.Comp.EQUAL, url).getFirst();
            if (book == null) {
                book = MQL.selectByID(Book.class, url);
            }
            return book;
        }
    }
}
