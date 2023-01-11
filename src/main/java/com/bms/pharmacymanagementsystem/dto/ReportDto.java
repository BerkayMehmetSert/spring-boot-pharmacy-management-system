package com.bms.pharmacymanagementsystem.dto;

public record ReportDto(
        String id,
        String customerId,
        String purchaserId,
        String saleId
) {
}
