package com.bms.pharmacymanagementsystem.service;

import com.bms.pharmacymanagementsystem.dto.SaleDto;
import com.bms.pharmacymanagementsystem.dto.converter.SaleDtoConverter;
import com.bms.pharmacymanagementsystem.exception.SaleListEmptyException;
import com.bms.pharmacymanagementsystem.exception.SaleNotFoundException;
import com.bms.pharmacymanagementsystem.model.Purchaser;
import com.bms.pharmacymanagementsystem.model.Sale;
import com.bms.pharmacymanagementsystem.repository.SaleRepository;
import com.bms.pharmacymanagementsystem.request.sale.CreateSaleRequest;
import com.bms.pharmacymanagementsystem.request.sale.UpdateSaleRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final CustomerService customerService;
    private final PharmacistService pharmacistService;
    private final MedicineService medicineService;
    private final PurchaserService purchaserService;
    private final SaleDtoConverter converter;

    public SaleService(SaleRepository saleRepository,
                       CustomerService customerService,
                       PharmacistService pharmacistService,
                       MedicineService medicineService,
                       PurchaserService purchaserService,
                       SaleDtoConverter converter) {
        this.saleRepository = saleRepository;
        this.customerService = customerService;
        this.pharmacistService = pharmacistService;
        this.medicineService = medicineService;
        this.purchaserService = purchaserService;
        this.converter = converter;
    }

    public void createSale(final CreateSaleRequest request) {
        Purchaser purchaser = purchaserService.getPurchaserById(request.getPurchaserId());

        Sale sale = new Sale(
                request.getCount(),
                calculateTotalPrice(request.getCount(), purchaser.getAmount()),
                customerService.getCustomerById(request.getCustomerId()),
                pharmacistService.getPharmacistById(request.getPharmacistId()),
                medicineService.getMedicineById(request.getMedicineId()),
                purchaser
        );

        saleRepository.save(sale);
    }

    public void updateSale(final String id, final UpdateSaleRequest request) {
        Purchaser purchaser = purchaserService.getPurchaserById(request.getPurchaserId());
        Sale sale = getSaleById(id);

        Sale updatedSale = new Sale(
                sale.getId(),
                request.getCount(),
                sale.getDate(),
                calculateTotalPrice(request.getCount(), purchaser.getAmount()),
                customerService.getCustomerById(request.getCustomerId()),
                pharmacistService.getPharmacistById(request.getPharmacistId()),
                medicineService.getMedicineById(request.getMedicineId()),
                purchaser
        );

        saleRepository.save(updatedSale);
    }

    public void updateSaleCountById(final String id, final Integer count) {
        Sale sale = getSaleById(id);

        Sale updatedSale = new Sale(
                sale.getId(),
                count,
                sale.getDate(),
                calculateTotalPrice(count, sale.getPurchaser().getAmount()),
                sale.getCustomer(),
                sale.getPharmacist(),
                sale.getMedicine(),
                sale.getPurchaser()
        );

        saleRepository.save(updatedSale);
    }

    public List<SaleDto> findAllSales() {
        List<Sale> sales = saleRepository.findAll();

        if (sales.isEmpty()) {
            throw new SaleListEmptyException("Sale list is empty.");
        }

        return converter.convert(sales);
    }

    protected Sale getSaleById(final String id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found by id: " + id));
    }

    private Double calculateTotalPrice(final Integer count, final Double price) {
        final double tax = 0.18;
        return (Double.valueOf(count) * price) +((Double.valueOf(count) * price) * tax);
    }
}
