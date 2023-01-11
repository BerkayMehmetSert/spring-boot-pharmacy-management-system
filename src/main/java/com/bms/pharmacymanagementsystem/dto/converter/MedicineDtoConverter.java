package com.bms.pharmacymanagementsystem.dto.converter;

import com.bms.pharmacymanagementsystem.dto.MedicineDto;
import com.bms.pharmacymanagementsystem.model.Medicine;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MedicineDtoConverter {
    public MedicineDto convert(Medicine from) {
        from.getCategory();
        return new MedicineDto(
                from.getId(),
                from.getName(),
                from.getDescription(),
                from.getPrice(),
                from.getCategory().getId()
        );
    }

    public Set<MedicineDto> convert(Set<Medicine> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    public List<MedicineDto> convert(List<Medicine> from) {
        return from.stream()
                .map(this::convert)
                .toList();
    }
}
