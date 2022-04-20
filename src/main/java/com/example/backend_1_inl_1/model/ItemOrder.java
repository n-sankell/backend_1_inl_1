package com.example.backend_1_inl_1.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class ItemOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate orderDate;

    @OneToOne
    @JoinColumn
    private Item item;

    public ItemOrder() {};

    public ItemOrder(LocalDate orderDate, Item item) {
        this.orderDate = orderDate;
        this.item = item;
    }

    public ItemOrder(long id, LocalDate orderDate, Item item) {
        this.id = id;
        this.orderDate = orderDate;
        this.item = item;
    }
}
