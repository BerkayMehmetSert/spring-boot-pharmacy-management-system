package com.bms.pharmacymanagementsystem.dto.converter;

import com.bms.pharmacymanagementsystem.dto.CustomerSaleDto;
import com.bms.pharmacymanagementsystem.model.Sale;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerSaleDtoConverter {
    public CustomerSaleDto convert(Sale from) {
        from.getPharmacist();
        from.getMedicine();
        from.getPurchaser();
        return new CustomerSaleDto(
                from.getId(),
                from.getCount(),
                from.getTotalAmount(),
                from.getPharmacist().getId(),
                from.getMedicine().getId(),
                from.getPurchaser().getId()
        );
    }

    public Set<CustomerSaleDto> convert(Set<Sale> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    public List<CustomerSaleDto> convert(List<Sale> from) {
        return from.stream()
                .map(this::convert)
                .toList();
    }
}
