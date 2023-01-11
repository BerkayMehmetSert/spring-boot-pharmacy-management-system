package com.bms.pharmacymanagementsystem.dto.converter;

import com.bms.pharmacymanagementsystem.dto.CustomerPurchaserDto;
import com.bms.pharmacymanagementsystem.model.Purchaser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerPurchaserDtoConverter {
    public CustomerPurchaserDto convert(Purchaser from) {
        from.getMedicine();
        return new CustomerPurchaserDto(
                from.getId(),
                from.getAmount(),
                from.getMedicine().getId()
        );
    }

    public Set<CustomerPurchaserDto> convert(Set<Purchaser> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    public List<CustomerPurchaserDto> convert(List<Purchaser> from) {
        return from.stream()
                .map(this::convert)
                .toList();
    }
}
