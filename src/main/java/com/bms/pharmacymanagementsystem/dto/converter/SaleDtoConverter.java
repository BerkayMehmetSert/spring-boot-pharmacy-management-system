package com.bms.pharmacymanagementsystem.dto.converter;

import com.bms.pharmacymanagementsystem.dto.SaleDto;
import com.bms.pharmacymanagementsystem.model.Sale;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SaleDtoConverter {
    public SaleDto convert(Sale from) {
        from.getCustomer();
        from.getPharmacist();
        from.getMedicine();
        from.getPurchaser();
        return new SaleDto(
                from.getId(),
                from.getCount(),
                from.getTotalAmount(),
                from.getCustomer().getId(),
                from.getPharmacist().getId(),
                from.getMedicine().getId(),
                from.getPurchaser().getId()
        );
    }

    public Set<SaleDto> convert(Set<Sale> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    public List<SaleDto> convert(List<Sale> from) {
        return from.stream()
                .map(this::convert)
                .toList();
    }
}
