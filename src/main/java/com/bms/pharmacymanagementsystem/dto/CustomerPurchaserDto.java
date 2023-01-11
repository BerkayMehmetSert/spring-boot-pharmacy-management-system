package com.bms.pharmacymanagementsystem.dto;

public record CustomerPurchaserDto(
        String id,
        Double amount,
        String medicineId
) {
}
