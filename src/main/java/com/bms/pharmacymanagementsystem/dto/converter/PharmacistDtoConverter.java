package com.bms.pharmacymanagementsystem.dto.converter;

import com.bms.pharmacymanagementsystem.dto.PharmacistDto;
import com.bms.pharmacymanagementsystem.model.Pharmacist;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PharmacistDtoConverter {
    private final PharmacistSaleDtoConverter saleDtoConverter;

    public PharmacistDtoConverter(PharmacistSaleDtoConverter saleDtoConverter) {
        this.saleDtoConverter = saleDtoConverter;
    }

    public PharmacistDto convert(Pharmacist from) {
        return new PharmacistDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getGender(),
                from.getAge(),
                from.getAddress(),
                from.getEmail(),
                from.getSales() != null ? saleDtoConverter.convert(from.getSales()) : null
        );
    }

    public Set<PharmacistDto> convert(Set<Pharmacist> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    public List<PharmacistDto> convert(List<Pharmacist> from) {
        return from.stream()
                .map(this::convert)
                .toList();
    }
}

