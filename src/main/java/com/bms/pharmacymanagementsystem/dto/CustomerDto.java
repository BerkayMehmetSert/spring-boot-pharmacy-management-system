package com.bms.pharmacymanagementsystem.dto;

import java.util.Set;

public record CustomerDto(
        String id,
        String firstName,
        String lastName,
        String gender,
        Integer age,
        String address,
        String email,
        Set<CustomerSaleDto> sales,
        Set<CustomerPurchaserDto> purchasers,
        Set<CustomerReportDto> reports
) {
}
