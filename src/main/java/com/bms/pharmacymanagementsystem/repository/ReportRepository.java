package com.bms.pharmacymanagementsystem.repository;

import com.bms.pharmacymanagementsystem.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, String> {
}