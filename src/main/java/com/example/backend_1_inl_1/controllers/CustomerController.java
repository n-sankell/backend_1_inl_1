package com.example.backend_1_inl_1.controllers;

import com.example.backend_1_inl_1.models.Customer;
import com.example.backend_1_inl_1.models.Response;
import com.example.backend_1_inl_1.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
                    : new Response<>("Customer not found.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Response<>("Please provide a valid number.");
        }
    }

    @PostMapping()
    public Response<?> addCustomer(@RequestBody Customer customer) {
        return checkCustomer(customer)
                ? new Response<>("Email is already in use!")
                : new Response<>("New customer "+customer.getName()+" was added");
    }

    private boolean checkCustomer(Customer customer) {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers.stream().anyMatch(c -> customer.getEmail().equals(c.getEmail())) || saveCustomer(customer);
    }

    private boolean saveCustomer(Customer customer) {
        customerRepository.save(customer);
        return false;
    }

}
