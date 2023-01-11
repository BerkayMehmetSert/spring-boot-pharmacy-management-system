package com.bms.pharmacymanagementsystem.controller;

import com.bms.pharmacymanagementsystem.dto.ReportDto;
import com.bms.pharmacymanagementsystem.request.report.CreateReportRequest;
import com.bms.pharmacymanagementsystem.request.report.UpdateReportRequest;
import com.bms.pharmacymanagementsystem.service.ReportService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reports")
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createReport(@RequestBody final CreateReportRequest request) {
        service.createReport(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/pdf")
    public ResponseEntity<InputStreamResource> generatePdf(@PathVariable String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(service.generatePdf(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReport(@PathVariable String id,
                                             @RequestBody UpdateReportRequest request) {
        service.updateReport(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> findReportById(@PathVariable String id) {
        return ResponseEntity.ok(service.findReportById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReportDto>> findAllReport() {
        return ResponseEntity.ok(service.findAllReports());
    }
}
