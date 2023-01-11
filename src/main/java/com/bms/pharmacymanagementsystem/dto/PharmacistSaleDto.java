package com.bms.pharmacymanagementsystem.dto;

public record PharmacistSaleDto(
        String id,
        Integer count,
        Double totalAmount,
        String customerId,
        String medicineId,
        String purchaserId
) {
}
