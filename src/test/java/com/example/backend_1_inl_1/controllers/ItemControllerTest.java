package com.example.backend_1_inl_1.controllers;

import com.example.backend_1_inl_1.models.Item;
import com.example.backend_1_inl_1.repositories.ItemRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    @Autowired
    private ItemRepository mockItemRepository;

    @BeforeEach
    public void init() {
        Item p1 = new Item(1,"AlbumOne","ArtistOne", LocalDate.of(1981,6,4),"Visor",46);
        Item p2 = new Item(2,"AlbumTwo","ArtistTwo", LocalDate.of(1982,6,4),"Visor",46);
        Item p3 = new Item(3,"AlbumThree","ArtistThree", LocalDate.of(1983,6,4),"Visor",46);
        Item p4 = new Item(4,"AlbumFour","ArtistFour", LocalDate.of(1984,6,4),"Visor",46);
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(p1));
        when(mockItemRepository.findById(2L)).thenReturn(Optional.of(p2));
        when(mockItemRepository.findAll()).thenReturn(Arrays.asList(p1,p2,p3));
        when(mockItemRepository.findById(4L)).thenReturn(Optional.of(p4));
    }

    @Test
    void getAllItems() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/items")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response", hasSize(3)))
                .andExpect(content().json(
                "{\"response\": [{\"id\": 1, \"albumName\": \"AlbumOne\", \"artist\": \"ArtistOne\", \"releaseDate\": \"1981-06-04\", \"genre\": \"Visor\", \"albumLength\": 46}, " +
                           "{\"id\": 2, \"albumName\": \"AlbumTwo\", \"artist\": \"ArtistTwo\", \"releaseDate\": \"1982-06-04\", \"genre\": \"Visor\", \"albumLength\": 46}, " +
                           "{\"id\": 3, \"albumName\": \"AlbumThree\", \"artist\": \"ArtistThree\", \"releaseDate\": \"1983-06-04\", \"genre\": \"Visor\", \"albumLength\": 46}]}"));;
    }

    @Test
    void getItemById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/items/1")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().json(
                        "{\"response\": {\"id\": 1, \"albumName\": \"AlbumOne\", \"artist\": \"ArtistOne\", \"releaseDate\": \"1981-06-04\", \"genre\": \"Visor\", \"albumLength\": 46}}"));
        mockMvc.perform(MockMvcRequestBuilders.get("/items/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"response\": {\"id\": 2, \"albumName\": \"AlbumTwo\", \"artist\": \"ArtistTwo\", \"releaseDate\": \"1982-06-04\", \"genre\": \"Visor\", \"albumLength\": 46}}"));
    }

    @Test
    void addItem() throws Exception {
        String json = "{\"id\":4,\"albumName\":\"AlbumFour\",\"artist\":\"ArtistFour\",\"releaseDate\":\"1984-06-04\",\"genre\":\"Visor\",\"albumLength\":46}";
        Item item4 = new Item(4,"AlbumFour","ArtistFour", LocalDate.of(1984,6,4),"Visor",46);

        assert(objectMapper.writeValueAsString(item4).equals(json));

        mockMvc.perform(MockMvcRequestBuilders.post("/items/").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response", notNullValue()))
                .andExpect(jsonPath("$.response", is(item4.getAlbumName() + "Was added to the database.")));

        Item result = mockItemRepository.findById(4L).isPresent() ? mockItemRepository.findById(4L).get() : null;
        assert(result != null);
        assert(result.equals(item4));

    }

}