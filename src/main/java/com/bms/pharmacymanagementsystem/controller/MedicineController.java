package com.bms.pharmacymanagementsystem.controller;

import com.bms.pharmacymanagementsystem.dto.MedicineDto;
import com.bms.pharmacymanagementsystem.request.medicine.CreateMedicineRequest;
import com.bms.pharmacymanagementsystem.request.medicine.UpdateMedicineRequest;
import com.bms.pharmacymanagementsystem.service.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/medicines")
public class MedicineController {
    private final MedicineService service;

    public MedicineController(MedicineService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createMedicine(@RequestBody CreateMedicineRequest request) {
        service.createMedicine(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMedicine(@PathVariable String id,
                                               @RequestBody UpdateMedicineRequest request) {
        service.updateMedicine(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/category")
    public ResponseEntity<Void> updateMedicineCategoryByCategoryId(@PathVariable String id,
                                                                   @RequestParam String categoryId) {
        service.updateMedicineCategoryByCategoryId(id, categoryId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletMedicine(@PathVariable String id) {
        service.deleteMedicine(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineDto> findMedicineById(@PathVariable String id) {
        return ResponseEntity.ok(service.findMedicineById(id));
    }

    @GetMapping
    public ResponseEntity<List<MedicineDto>> findAllMedicines() {
        return ResponseEntity.ok(service.findAllMedicines());
    }
}
