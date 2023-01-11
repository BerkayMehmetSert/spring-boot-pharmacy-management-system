package com.bms.pharmacymanagementsystem.dto;

public record CustomerSaleDto(
        String id,
        Integer count,
        Double totalAmount,
        String pharmacistId,
        String medicineId,
        String purchaserId
) {
}
