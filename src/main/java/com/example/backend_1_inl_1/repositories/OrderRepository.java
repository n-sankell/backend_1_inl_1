package com.example.backend_1_inl_1.repositories;

import com.example.backend_1_inl_1.models.ItemOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<ItemOrder, Long> {
}
