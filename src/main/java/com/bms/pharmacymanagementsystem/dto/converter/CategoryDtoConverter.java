package com.bms.pharmacymanagementsystem.dto.converter;

import com.bms.pharmacymanagementsystem.dto.CategoryDto;
import com.bms.pharmacymanagementsystem.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryDtoConverter {
    public CategoryDto convert(Category from) {
        return new CategoryDto(
                from.getId(),
                from.getName()
        );
    }

    public Set<CategoryDto> convert(Set<Category> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    public List<CategoryDto> convert(List<Category> from) {
        return from.stream()
                .map(this::convert)
                .toList();
    }
}
