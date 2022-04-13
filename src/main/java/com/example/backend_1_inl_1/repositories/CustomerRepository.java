package com.example.backend_1_inl_1.repositories;

import com.example.backend_1_inl_1.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
