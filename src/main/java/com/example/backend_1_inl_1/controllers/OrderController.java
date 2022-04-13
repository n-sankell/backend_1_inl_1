package com.example.backend_1_inl_1.controllers;

import com.example.backend_1_inl_1.models.Order;
import com.example.backend_1_inl_1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping("/orders")
    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
