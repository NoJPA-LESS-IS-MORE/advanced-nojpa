package dk.lessismore.advnojpa.service;

import dk.lessismore.advnojpa.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by niakoi on 4/3/15.
 */
public interface PersonService {

    Person create(String name);

    List<Person> get();

    Page<Person> get(Pageable p);
}
