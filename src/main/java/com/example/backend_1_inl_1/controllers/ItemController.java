package com.example.backend_1_inl_1.controllers;

import com.example.backend_1_inl_1.models.*;
import com.example.backend_1_inl_1.repositories.CustomerRepository;
import com.example.backend_1_inl_1.repositories.ItemRepository;
import com.example.backend_1_inl_1.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "https://n-sankell.github.io")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping()
    public Response<Iterable<Item>> getAllItems() {
        return new Response<>(itemRepository.findAll());
    }

    @GetMapping("{id}")
    public Response<?> getItemById(@PathVariable String id) {
        try {
            long parsedId = Long.parseLong(id);
            return itemRepository.findById(parsedId).isPresent()
                    ? new Response<>(itemRepository.findById(parsedId).get())
                    : new Response<>("Item not found.");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new Response<>("Please provide a valid number.");
        }
    }

    @PostMapping()
    public Response<String> addItem(@RequestBody Item item) {
        itemRepository.save(item);
        return new Response<>(item.getAlbumName() + "Was added to the database.");
    }

    @PostMapping("/buy")
    public Response<String>buyItem(@RequestBody Purchase purchase) {
        if (customerRepository.existsById(purchase.customerId()) && itemRepository.existsById(purchase.itemId())) {
            ItemOrder newOrder = new ItemOrder(LocalDate.now(), itemRepository.findById(purchase.itemId()).get());
            Customer customer = customerRepository.findById(purchase.customerId()).get();
            orderRepository.save(newOrder);
            customer.addOrder(newOrder);
            customerRepository.save(customer);
            return new Response<>("Purchase complete.");
        } else if (customerRepository.existsById(purchase.customerId())) {
            return new Response<>("Product was not found.");
        } else if (itemRepository.existsById(purchase.itemId())) {
            return new Response<>("Customer was not found.");
        } else {
            return new Response<>("No product or customer was found.");
        }
    }


}


