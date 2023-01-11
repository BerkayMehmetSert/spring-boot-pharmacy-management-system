package com.bms.pharmacymanagementsystem.dto.converter;

import com.bms.pharmacymanagementsystem.dto.PurchaserDto;
import com.bms.pharmacymanagementsystem.model.Purchaser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PurchaserDtoConverter {
    public PurchaserDto convert(Purchaser from) {
        from.getMedicine();
        from.getCustomer();
        return new PurchaserDto(
                from.getId(),
                from.getAmount(),
                from.getMedicine().getId(),
                from.getCustomer().getId()
        );
    }

    public Set<PurchaserDto> convert(Set<Purchaser> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    public List<PurchaserDto> convert(List<Purchaser> from) {
        return from.stream()
                .map(this::convert)
                .toList();
    }
}
