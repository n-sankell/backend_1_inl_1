package com.example.backend_1_inl_1.controllers;

import com.example.backend_1_inl_1.models.Item;
import com.example.backend_1_inl_1.models.Response;
import com.example.backend_1_inl_1.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "https://n-sankell.github.io")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping()
    public Response<Iterable<Item>> getAllItems() {
        return new Response<>(itemRepository.findAll());
    }

    @RequestMapping("{id}")
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

}


