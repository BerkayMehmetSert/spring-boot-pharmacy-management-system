package com.bms.pharmacymanagementsystem.service;

import com.bms.pharmacymanagementsystem.core.utilities.PdfService;
import com.bms.pharmacymanagementsystem.dto.ReportDto;
import com.bms.pharmacymanagementsystem.dto.converter.ReportDtoConverter;
import com.bms.pharmacymanagementsystem.exception.ReportListEmptyException;
import com.bms.pharmacymanagementsystem.exception.ReportNotFoundException;
import com.bms.pharmacymanagementsystem.model.Report;
import com.bms.pharmacymanagementsystem.repository.ReportRepository;
import com.bms.pharmacymanagementsystem.request.report.CreateReportRequest;
import com.bms.pharmacymanagementsystem.request.report.UpdateReportRequest;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final CustomerService customerService;
    private final PurchaserService purchaserService;
    private final SaleService saleService;
    private final PdfService pdfService;
    private final ReportDtoConverter converter;

    public ReportService(ReportRepository reportRepository,
                         CustomerService customerService,
                         PurchaserService purchaserService,
                         SaleService saleService,
                         PdfService pdfService,
                         ReportDtoConverter converter) {
        this.reportRepository = reportRepository;
        this.customerService = customerService;
        this.purchaserService = purchaserService;
        this.saleService = saleService;
        this.pdfService = pdfService;
        this.converter = converter;
    }

    public void createReport(final CreateReportRequest request) {
        Report report = new Report(
                customerService.getCustomerById(request.getCustomerId()),
                purchaserService.getPurchaserById(request.getPurchaserId()),
                saleService.getSaleById(request.getSaleId())
        );

        reportRepository.save(report);
    }

    public ByteArrayInputStream generatePdf(final String id) {
        return pdfService.generatePdf(getReportById(id));
    }

    public void updateReport(final String id, final UpdateReportRequest request) {
        Report report = getReportById(id);

        Report updatetReport = new Report(
                report.getId(),
                report.getDate(),
                customerService.getCustomerById(request.getCustomerId()),
                purchaserService.getPurchaserById(request.getPurchaserId()),
                saleService.getSaleById(request.getSaleId())
        );

        reportRepository.save(updatetReport);
    }

    public ReportDto findReportById(final String id) {
        return converter.convert(getReportById(id));
    }

    public List<ReportDto> findAllReports() {
        List<Report> reports = reportRepository.findAll();

        if (reports.isEmpty()) {
            throw new ReportListEmptyException("Report list is empty.");
        }

        return converter.convert(reports);
    }

    private Report getReportById(final String id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException("Report not found by id:" + id));
    }
}
