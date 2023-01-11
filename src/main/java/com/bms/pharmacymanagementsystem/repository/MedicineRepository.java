package com.bms.pharmacymanagementsystem.repository;

import com.bms.pharmacymanagementsystem.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, String> {
    boolean existsByNameIgnoreCase(String name);
}