package dk.lessismore.advnojpa.model;

import dk.lessismore.nojpa.reflection.db.model.ModelObjectInterface;

/**
 * Created by niakoi on 4/3/15.
 */
public interface Book extends ModelObjectInterface {

    String getTitle();
    void setTitle(String title);

    Person getWriter();
    void setWriter(Person writer);

}
