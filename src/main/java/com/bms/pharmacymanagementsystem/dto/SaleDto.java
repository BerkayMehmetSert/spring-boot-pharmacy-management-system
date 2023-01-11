package com.bms.pharmacymanagementsystem.dto;

public record SaleDto(
        String id,
        Integer count,
        Double totalAmount,
        String customerId,
        String pharmacistId,
        String medicineId,
        String purchaserId
) {
}
