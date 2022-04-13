package com.example.backend_1_inl_1.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
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
    private List<Order> orders;
}
