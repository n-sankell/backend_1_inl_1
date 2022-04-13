package com.example.backend_1_inl_1.controllers;

import com.example.backend_1_inl_1.models.Customer;
import com.example.backend_1_inl_1.models.Response;
import com.example.backend_1_inl_1.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/customers")
    public Response<Iterable<Customer>> getAllCustomers() {
        return new Response<>(customerRepository.findAll());
    }

    @RequestMapping("/customers/id")
    public Customer getCustomerById(@RequestParam Long id) {
        return customerRepository.findById(id).isPresent() ? customerRepository.findById(id).get() : new Customer();
    }

}
