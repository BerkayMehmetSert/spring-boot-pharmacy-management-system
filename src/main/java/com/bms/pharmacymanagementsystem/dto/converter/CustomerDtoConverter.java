package com.bms.pharmacymanagementsystem.dto.converter;

import com.bms.pharmacymanagementsystem.dto.CustomerDto;
import com.bms.pharmacymanagementsystem.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {
    private final CustomerSaleDtoConverter saleDtoConverter;
    private final CustomerPurchaserDtoConverter purchaserDtoConverter;
    private final CustomerReportDtoConverter reportDtoConverter;

    public CustomerDtoConverter(CustomerSaleDtoConverter saleDtoConverter,
                                CustomerPurchaserDtoConverter purchaserDtoConverter,
                                CustomerReportDtoConverter reportDtoConverter) {
        this.saleDtoConverter = saleDtoConverter;
        this.purchaserDtoConverter = purchaserDtoConverter;
        this.reportDtoConverter = reportDtoConverter;
    }

    public CustomerDto convert(Customer from) {
        return new CustomerDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getGender(),
                from.getAge(),
                from.getAddress(),
                from.getEmail(),
                from.getSales() != null ? saleDtoConverter.convert(from.getSales()) : null,
                from.getPurchasers() != null ? purchaserDtoConverter.convert(from.getPurchasers()) : null,
                from.getReports() != null ? reportDtoConverter.convert(from.getReports()) : null
        );
    }

    public Set<CustomerDto> convert(Set<Customer> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    public List<CustomerDto> convert(List<Customer> from) {
        return from.stream()
                .map(this::convert)
                .toList();
    }
}
