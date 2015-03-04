package dk.lessismore.advnojpa.model;

import dk.lessismore.nojpa.reflection.db.annotations.SearchField;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectInterface;

/**
 * Created by niakoi on 4/3/15.
 */
public interface Address extends ModelObjectInterface {
    @SearchField
    String getStreet();
    void setStreet(String street);
}
