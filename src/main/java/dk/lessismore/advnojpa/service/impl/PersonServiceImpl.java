package dk.lessismore.advnojpa.service.impl;

import dk.lessismore.advnojpa.model.Person;
import dk.lessismore.advnojpa.service.PersonService;
import dk.lessismore.nojpa.db.methodquery.MQL;
import dk.lessismore.nojpa.db.methodquery.NList;
import dk.lessismore.nojpa.db.methodquery.NQL;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by niakoi on 4/3/15.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public Person create(String name, Person parent) {
        Person person = ModelObjectService.create(Person.class);
        person.setName(name);
        person.setParent(parent);
        parent.setChildren(ModelObjectService.addObjectToArray(parent.getChildren(), person)); // TODO is parent saved?
        ModelObjectService.save(person);
        return person;
    }

    @Override
    public List<Person> get() {
        return MQL.select(Person.class).getList();
    }

    @Override
    public Page<Person> get(Pageable p) {
        NList<Person> list = NQL.search(Person.class).limit(p.getOffset(), p.getOffset() + p.getPageSize()).getList();
        return new PageImpl<>(list, p, list.getNumberFound());
    }
}
