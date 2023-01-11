package com.bms.pharmacymanagementsystem.repository;

import com.bms.pharmacymanagementsystem.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, String> {
}