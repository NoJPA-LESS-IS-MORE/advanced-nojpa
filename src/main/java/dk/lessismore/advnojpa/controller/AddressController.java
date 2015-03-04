package dk.lessismore.advnojpa.controller;

import dk.lessismore.advnojpa.model.Address;
import dk.lessismore.advnojpa.model.Person;
import dk.lessismore.advnojpa.service.AddressService;
import dk.lessismore.advnojpa.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by niakoi on 4/3/15.
 */
@RestController
@RequestMapping("/people/{person}/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private PersonService personService;

    @RequestMapping
    public Address get(@PathVariable Person person) {
        return person.getAddress();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Address save(@PathVariable Person person, String street) {

        personService.lives(person, addressService.create(street));
        return person.getAddress();
    }


}
