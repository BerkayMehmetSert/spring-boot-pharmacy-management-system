package com.bms.pharmacymanagementsystem.dto;

public record MedicineDto(
        String id,
        String name,
        String description,
        Double price,
        String categoryId
) {
}
