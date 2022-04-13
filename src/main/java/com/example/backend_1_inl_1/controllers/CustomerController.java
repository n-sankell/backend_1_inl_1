package com.example.backend_1_inl_1.controllers;

import com.example.backend_1_inl_1.models.Customer;
import com.example.backend_1_inl_1.models.Response;
import com.example.backend_1_inl_1.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping()
    public Response<Iterable<Customer>> getAllCustomers() {
        return new Response<>(customerRepository.findAll());
    }

    @RequestMapping("{id}")
    public Response<?> getCustomerById(@PathVariable String id) {
        try {
            long parsedId = Long.parseLong(id);
            return customerRepository.findById(parsedId).isPresent()
                    ? new Response<>(customerRepository.findById(parsedId).get())
                    : new Response<>("Customer not found.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Response<>("Please provide a valid number.");
        }
    }

    @PostMapping()
    @CrossOrigin (origins = "http://127.0.0.1:5500")
    public Response<?> addCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        return new Response<>(customer.getName());
    }

}
