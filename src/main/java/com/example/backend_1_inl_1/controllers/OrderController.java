package com.example.backend_1_inl_1.controllers;

import com.example.backend_1_inl_1.dto.ResponsMessage;
import com.example.backend_1_inl_1.model.ItemOrder;
import com.example.backend_1_inl_1.dto.Response;
import com.example.backend_1_inl_1.repositories.CustomerRepository;
import com.example.backend_1_inl_1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "https://n-sankell.github.io")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping()
    public Response<Iterable<ItemOrder>> getAllOrders() {
        return new Response<>(orderRepository.findAll());
    }

    @GetMapping("{id}")
    public Response<?> getOrdersByCustomerId(@PathVariable String id) {
        try {
            long parsedId = Long.parseLong(id);
            return customerRepository.findById(parsedId).isPresent()
                    ? new Response<>(customerRepository.findById(parsedId).get().getItemOrders())
                    : new Response<>(ResponsMessage.CUSTOMER_NOT_FOUND.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Response<>(ResponsMessage.NOT_A_NUMBER.getMessage());
        }
    }

}
