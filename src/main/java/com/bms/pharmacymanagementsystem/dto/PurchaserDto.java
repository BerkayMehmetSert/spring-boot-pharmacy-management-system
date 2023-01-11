package com.bms.pharmacymanagementsystem.dto;

public record PurchaserDto(
        String id,
        Double amount,
        String medicineId,
        String customerId
) {
}
