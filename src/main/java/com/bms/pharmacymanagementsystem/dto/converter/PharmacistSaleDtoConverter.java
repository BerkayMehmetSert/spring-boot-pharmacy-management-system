package com.bms.pharmacymanagementsystem.dto.converter;

import com.bms.pharmacymanagementsystem.dto.PharmacistSaleDto;
import com.bms.pharmacymanagementsystem.model.Sale;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PharmacistSaleDtoConverter {
    public PharmacistSaleDto convert(Sale from) {
        from.getCustomer();
        from.getMedicine();
        from.getPurchaser();
        return new PharmacistSaleDto(
                from.getId(),
                from.getCount(),
                from.getTotalAmount(),
                from.getCustomer().getId(),
                from.getMedicine().getId(),
                from.getPurchaser().getId()
        );
    }

    public Set<PharmacistSaleDto> convert(Set<Sale> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    public List<PharmacistSaleDto> convert(List<Sale> from) {
        return from.stream()
                .map(this::convert)
                .toList();
    }
}
