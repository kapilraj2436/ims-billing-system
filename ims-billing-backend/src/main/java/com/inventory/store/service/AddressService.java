package com.inventory.store.service;

import com.inventory.store.model.Address;
import com.inventory.store.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityService cityService;


    public Address getAddressById(int customerAddressId) {
        Address address = addressRepository.getAddressById(customerAddressId);
        address.setCity(cityService.getCityById(address.getCity().getCityId()));
        return address;
    }

    public int addAddress(Address address) {
        //int cityId = cityService.addCity(address.getCity());
        //address.getCity().setCityId(cityId);
        return addressRepository.addAddress(address);
    }

    public List<Address> getAllAddress() {
        List<Address> addresses = addressRepository.getAllAddress();
        for (Address address : addresses) {
            address.setCity(cityService.getCityById(address.getCity().getCityId()));
        }
        return addresses;
    }

    public List<Address> getAllAddressByIds(List<Integer> addressIds) {
        List<Address> addresses = addressRepository.getAllAddressByIds(addressIds);
        for (Address address : addresses) {
            address.setCity(cityService.getCityById(address.getCity().getCityId()));
        }
        return addresses;
    }
}
