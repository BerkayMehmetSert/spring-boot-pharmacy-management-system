package com.bms.pharmacymanagementsystem.controller;

import com.bms.pharmacymanagementsystem.dto.SaleDto;
import com.bms.pharmacymanagementsystem.request.sale.CreateSaleRequest;
import com.bms.pharmacymanagementsystem.request.sale.UpdateSaleRequest;
import com.bms.pharmacymanagementsystem.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/sales")
public class SaleController {
    private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createSale(@RequestBody CreateSaleRequest request) {
        service.createSale(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSale(@PathVariable String id,
                                           @RequestBody UpdateSaleRequest request) {
        service.updateSale(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/count")
    public ResponseEntity<Void> updateSaleCountById(@PathVariable String id,
                                                    @RequestParam Integer count) {
        service.updateSaleCountById(id, count);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<SaleDto>> findAllSales() {
        return ResponseEntity.ok(service.findAllSales());
    }
}
