package com.bms.pharmacymanagementsystem.repository;

import com.bms.pharmacymanagementsystem.model.Purchaser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaserRepository extends JpaRepository<Purchaser, String> {
}