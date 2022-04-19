package com.example.backend_1_inl_1.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String address;
    private LocalDate birthDate;

    @OneToMany
    @JoinColumn
    private List<ItemOrder> itemOrders = new ArrayList<>();

    public Customer(){};

    public Customer(String name, String email, String address, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
    }

    public void addOrder(ItemOrder order) {
        itemOrders.add(order);
    }

    public Customer(long id, String name, String email, String address, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
    }
}
