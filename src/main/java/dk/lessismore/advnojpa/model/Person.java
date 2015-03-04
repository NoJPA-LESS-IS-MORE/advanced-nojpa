package dk.lessismore.advnojpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectInterface;

/**
 * Created by niakoi on 4/3/15.
 */
public interface Person extends ModelObjectInterface {

    String getName();
    void setName(String name);

    Person getParent();
    void setParent(Person parent);

    @JsonIgnore
    Person[] getChildren();
    void setChildren(Person[] children);

}
