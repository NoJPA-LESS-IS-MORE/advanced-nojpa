package dk.lessismore.advnojpa.model;

import dk.lessismore.nojpa.reflection.db.annotations.SearchField;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectInterface;

/**
 * Created by niakoi on 4/3/15.
 */
public interface Book extends ModelObjectInterface {

    @SearchField
    String getTitle();
    void setTitle(String title);

    @SearchField
    Person getWriter();
    void setWriter(Person writer);

}
