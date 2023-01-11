package com.bms.pharmacymanagementsystem.controller;

import com.bms.pharmacymanagementsystem.dto.PharmacistDto;
import com.bms.pharmacymanagementsystem.request.pharmacist.CreatePharmacistRequest;
import com.bms.pharmacymanagementsystem.request.pharmacist.UpdatePharmacistRequest;
import com.bms.pharmacymanagementsystem.service.PharmacistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pharmacists")
public class PharmacistController {
    private final PharmacistService service;

    public PharmacistController(PharmacistService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createPharmacist(@RequestBody CreatePharmacistRequest request) {
        service.createPharmacist(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePharmacist(@PathVariable String id,
                                                 @RequestBody UpdatePharmacistRequest request) {
        service.updatePharmacist(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePharmacist(@PathVariable String id) {
        service.deletePharmacist(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PharmacistDto> findPharmacistById(@PathVariable String id) {
        return ResponseEntity.ok(service.findPharmacistById(id));
    }

    @GetMapping
    public ResponseEntity<List<PharmacistDto>> findAllPharmacists() {
        return ResponseEntity.ok(service.findAllPharmacists());
    }
}
