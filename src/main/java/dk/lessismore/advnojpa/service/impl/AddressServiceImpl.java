package dk.lessismore.advnojpa.service.impl;

import dk.lessismore.advnojpa.model.Address;
import dk.lessismore.advnojpa.service.AddressService;
import dk.lessismore.nojpa.reflection.db.model.ModelObjectService;
import org.springframework.stereotype.Service;

/**
 * Created by niakoi on 4/3/15.
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Override
    public Address create(String street) {
        Address address = ModelObjectService.create(Address.class);
        address.setStreet(street);
        ModelObjectService.save(address);
        return address;
    }
}
