package com.bms.pharmacymanagementsystem.dto.converter;

import com.bms.pharmacymanagementsystem.dto.ReportDto;
import com.bms.pharmacymanagementsystem.model.Report;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ReportDtoConverter {
    public ReportDto convert(Report from) {
        from.getCustomer();
        from.getPurchaser();
        from.getSale();
        return new ReportDto(
                from.getId(),
                from.getCustomer().getId(),
                from.getPurchaser().getId(),
                from.getSale().getId()
        );
    }

    public Set<ReportDto> convert(Set<Report> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    public List<ReportDto> convert(List<Report> from) {
        return from.stream()
                .map(this::convert)
                .toList();
    }
}
