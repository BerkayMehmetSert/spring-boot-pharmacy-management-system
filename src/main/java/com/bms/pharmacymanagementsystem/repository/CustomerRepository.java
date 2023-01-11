package com.bms.pharmacymanagementsystem.repository;

import com.bms.pharmacymanagementsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    boolean existsByEmailIgnoreCase(String email);
}