package com.bms.pharmacymanagementsystem.controller;

import com.bms.pharmacymanagementsystem.dto.PurchaserDto;
import com.bms.pharmacymanagementsystem.request.purchase.CreatePurchaserRequest;
import com.bms.pharmacymanagementsystem.request.purchase.UpdatePurchaserRequest;
import com.bms.pharmacymanagementsystem.service.PurchaserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/purchasers")
public class PurchaserController {
    private final PurchaserService service;

    public PurchaserController(PurchaserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createPurchaser(@RequestBody CreatePurchaserRequest request) {
        service.createPurchaser(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePurchaser(@PathVariable String id,
                                                @RequestBody UpdatePurchaserRequest request) {
        service.updatePurchaser(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaser(@PathVariable String id) {
        service.deletePurchaser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaserDto> findPurchaserById(@PathVariable String id) {
        return ResponseEntity.ok(service.findPurchaserById(id));
    }

    @GetMapping
    public ResponseEntity<List<PurchaserDto>> findAllPurchasers() {
        return ResponseEntity.ok(service.findAllPurchasers());
    }
}
