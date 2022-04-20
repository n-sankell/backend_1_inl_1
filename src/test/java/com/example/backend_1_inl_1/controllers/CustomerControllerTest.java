package com.example.backend_1_inl_1.controllers;

import com.example.backend_1_inl_1.model.Customer;
import com.example.backend_1_inl_1.repositories.CustomerRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    @Autowired
    private CustomerRepository mockCustomerRepository;

    @BeforeEach
    void init(){
        Customer customer1 = new Customer(1,"Eva","evaave@gmail.com","Lilla sällskapets väg 57", LocalDate.of(1965,3,15));
        Customer customer2 = new Customer(2,"Olle","ollis@gmail.com","Kungsgatan 34", LocalDate.of(1948,8,22));
        Customer customer3 = new Customer(3,"Adam","adams@gmail.com","Johannesvägen 23", LocalDate.of(1975,5,23));
        when(mockCustomerRepository.findById(1L)).thenReturn(Optional.of(customer1));
        when(mockCustomerRepository.findById(2L)).thenReturn(Optional.of(customer2));
        when(mockCustomerRepository.findById(3L)).thenReturn(Optional.of(customer3));
        when(mockCustomerRepository.findAll()).thenReturn(Arrays.asList(customer1,customer2,customer3));
    }

    @Test
    void getAllCustomers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response", hasSize(3)))
                .andExpect(content().json("{response:[{\"id\":1,\"name\":\"Eva\",\"email\":\"evaave@gmail.com\",\"address\":\"Lilla sällskapets väg 57\",\"birthDate\":\"1965-03-15\",\"itemOrders\":[]},{\"id\":2,\"name\":\"Olle\",\"email\":\"ollis@gmail.com\",\"address\":\"Kungsgatan 34\",\"birthDate\":\"1948-08-22\",\"itemOrders\":[]},{\"id\":3,\"name\":\"Adam\",\"email\":\"adams@gmail.com\",\"address\":\"Johannesvägen 23\",\"birthDate\":\"1975-05-23\",\"itemOrders\":[]}]}"));
    }

    @Test
    void getCustomerById() {

    }

    @Test
    void addCustomer() {

    }

}