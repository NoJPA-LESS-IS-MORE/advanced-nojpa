package dk.lessismore.advnojpa.controller;

import dk.lessismore.advnojpa.helper.UrlHelper;
import dk.lessismore.advnojpa.model.Book;
import dk.lessismore.advnojpa.model.Person;
import dk.lessismore.advnojpa.service.BookService;
import dk.lessismore.advnojpa.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Created by niakoi on 4/3/15.
 */
@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private BookService bookService;

    @RequestMapping
    public Page<Person> paging(@PageableDefault Pageable p) {
        return personService.get(p);
    }

    @RequestMapping("{person}")
    public Person person(@PathVariable Person person) {
        return person;
    }

    @RequestMapping(method = RequestMethod.POST)
    public View create(@RequestParam String name) {
        Person person = personService.create(name);
        return new RedirectView(UrlHelper.personUrl(person));
    }


}
