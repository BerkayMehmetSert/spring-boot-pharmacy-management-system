package com.bms.pharmacymanagementsystem.repository;

import com.bms.pharmacymanagementsystem.model.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacistRepository extends JpaRepository<Pharmacist, String> {
    boolean existsByEmail(String email);
}