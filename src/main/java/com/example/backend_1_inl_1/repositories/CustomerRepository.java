package com.example.backend_1_inl_1.repositories;

import com.example.backend_1_inl_1.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
