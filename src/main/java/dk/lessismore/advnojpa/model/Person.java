package dk.lessismore.advnojpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dk.lessismore.nojpa.db.methodquery.MQL;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectInterface;
import dk.lessismore.nojpa.rest.Locator;
import dk.lessismore.nojpa.rest.ObjectLocator;
import dk.lessismore.nojpa.rest.ObjectPrinter;
import dk.lessismore.nojpa.rest.Printer;

/**
 * Created by niakoi on 4/3/15.
 */
@Locator(locator = Person.Locator.class)
@Printer(printer = Person.Printer.class)
public interface Person extends ModelObjectInterface {

    String getName();
    void setName(String name);

    Person getParent();
    void setParent(Person parent);

    @JsonIgnore
    Person[] getChildren();
    void setChildren(Person[] children);

    public static class Locator implements ObjectLocator<Person> {
        @Override
        public Person get(String url) {
            Person mPerson = MQL.mock(Person.class);
            Person person = MQL.select(mPerson).where(mPerson.getName(), MQL.Comp.EQUAL, url).getFirst();
            if (person == null) {
                person = MQL.selectByID(Person.class, url);
            }
            return person;
        }
    }

    public static class Printer implements ObjectPrinter<Person> {
        @Override
        public String put(Person person) {
            return person.getName();
        }
    }
}
