package com.example.backend_1_inl_1.controllers;

import com.example.backend_1_inl_1.dto.ResponsMessage;
import com.example.backend_1_inl_1.model.Customer;
import com.example.backend_1_inl_1.dto.Response;
import com.example.backend_1_inl_1.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "https://n-sankell.github.io")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping()
    public Response<Iterable<Customer>> getAllCustomers() {
        return new Response<>(customerRepository.findAll());
    }

    @GetMapping("{id}")
    public Response<?> getCustomerById(@PathVariable String id) {
        try {
            long parsedId = Long.parseLong(id);
            return customerRepository.findById(parsedId).isPresent()
                    ? new Response<>(customerRepository.findById(parsedId).get())
                    : new Response<>(ResponsMessage.CUSTOMER_NOT_FOUND.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Response<>(ResponsMessage.NOT_A_NUMBER.getMessage());
        }
    }

    @PostMapping()
    public Response<String> addCustomer(@RequestBody Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            return new Response<>(ResponsMessage.EMAIL_IN_USE.getMessage());
        } else {
            customerRepository.save(customer);
            return new Response<>(customer.getName() + ResponsMessage.CUSTOMER_ADDED.getMessage());
        }
    }

}