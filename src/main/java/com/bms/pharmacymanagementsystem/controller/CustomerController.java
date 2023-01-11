package com.bms.pharmacymanagementsystem.controller;

import com.bms.pharmacymanagementsystem.dto.CustomerDto;
import com.bms.pharmacymanagementsystem.request.customer.CreateCustomerRequest;
import com.bms.pharmacymanagementsystem.request.customer.UpdateCustomerRequest;
import com.bms.pharmacymanagementsystem.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CreateCustomerRequest request) {
        service.createCustomer(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable String id,
                                               @RequestBody UpdateCustomerRequest request) {
        service.updateCustomer(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        service.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable String id) {
        return ResponseEntity.ok(service.findCustomerById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        return ResponseEntity.ok(service.findAllCustomers());
    }

}
