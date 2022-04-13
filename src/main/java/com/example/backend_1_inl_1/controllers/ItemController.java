package com.example.backend_1_inl_1.controllers;


import com.example.backend_1_inl_1.models.Item;
import com.example.backend_1_inl_1.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping("/items")
    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

}


