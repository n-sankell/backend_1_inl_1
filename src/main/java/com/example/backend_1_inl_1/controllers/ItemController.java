package com.example.backend_1_inl_1.controllers;

import com.example.backend_1_inl_1.models.Disc;
import com.example.backend_1_inl_1.models.Item;
import com.example.backend_1_inl_1.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping("/items")
    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping
    public List<Disc>getDisc(){
        return List.of(new Disc(
                1L,
                "Groda",
                "Greta Gris",
                LocalDate.of(1976, Month.JANUARY, 5),
                "Classic Rock",
                65));

    }

}


