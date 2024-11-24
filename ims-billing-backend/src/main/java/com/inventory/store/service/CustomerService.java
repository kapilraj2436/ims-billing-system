package com.inventory.store.service;

import com.inventory.store.model.Address;
import com.inventory.store.model.Customer;
import com.inventory.store.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressService addressService;

    public Customer getCustomerById(int customerId) {

        Customer customer = customerRepository.getCustomerById(customerId);
        Address address = addressService.getAddressById(customer.getCustomerAddress().getAddressId());
        customer.setCustomerAddress(address);
        return customer;

    }

    public void addCustomer(Customer customer) {
//        List<Integer> addressIds = new ArrayList<>();
//        for (Address address : customer.getCustomerAddress()) {
//            addressIds.add(addressService.addAddress(address));
//        }
        int customerId = customerRepository.addCustomer(customer);
        //customerRepository.addCustomerAddress(customerId);
    }


    public List<Customer> getAllCustomer() {
        List<Customer> customers = customerRepository.getAllCustomer();
        for (Customer customer : customers) {
            Address address = addressService.getAddressById(customer.getCustomerAddress().getAddressId());
            customer.setCustomerAddress(address);
        }
        return customers;
    }
}
