package com.bms.pharmacymanagementsystem.dto.converter;

import com.bms.pharmacymanagementsystem.dto.CustomerReportDto;
import com.bms.pharmacymanagementsystem.model.Report;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerReportDtoConverter {
    public CustomerReportDto convert(Report from) {
        from.getPurchaser();
        from.getSale();
        return new CustomerReportDto(
                from.getId(),
                from.getPurchaser().getId(),
                from.getSale().getId()
        );
    }

    public Set<CustomerReportDto> convert(Set<Report> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    public List<CustomerReportDto> convert(List<Report> from) {
        return from.stream()
                .map(this::convert)
                .toList();
    }
}
