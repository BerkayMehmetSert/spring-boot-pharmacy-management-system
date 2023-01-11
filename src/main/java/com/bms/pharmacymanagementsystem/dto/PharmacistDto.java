package com.bms.pharmacymanagementsystem.dto;

import java.util.Set;

public record PharmacistDto(
        String id,
        String firstName,
        String lastName,
        String gender,
        Integer age,
        String address,
        String email,
        Set<PharmacistSaleDto> sales
) {
}
