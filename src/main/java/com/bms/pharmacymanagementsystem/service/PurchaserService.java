package com.bms.pharmacymanagementsystem.service;

import com.bms.pharmacymanagementsystem.dto.PurchaserDto;
import com.bms.pharmacymanagementsystem.dto.converter.PurchaserDtoConverter;
import com.bms.pharmacymanagementsystem.exception.PurchaserListEmptyException;
import com.bms.pharmacymanagementsystem.exception.PurchaserNotFoundException;
import com.bms.pharmacymanagementsystem.model.Medicine;
import com.bms.pharmacymanagementsystem.model.Purchaser;
import com.bms.pharmacymanagementsystem.repository.PurchaserRepository;
import com.bms.pharmacymanagementsystem.request.purchase.CreatePurchaserRequest;
import com.bms.pharmacymanagementsystem.request.purchase.UpdatePurchaserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaserService {
    private final PurchaserRepository purchaserRepository;
    private final MedicineService medicineService;
    private final CustomerService customerService;
    private final PurchaserDtoConverter converter;

    public PurchaserService(PurchaserRepository purchaserRepository,
                            MedicineService medicineService,
                            CustomerService customerService,
                            PurchaserDtoConverter converter) {
        this.purchaserRepository = purchaserRepository;
        this.medicineService = medicineService;
        this.customerService = customerService;
        this.converter = converter;
    }

    public void createPurchaser(final CreatePurchaserRequest request) {
        Medicine medicine = medicineService.getMedicineById(request.getMedicineId());

        Purchaser purchaser = new Purchaser(
                medicine.getPrice(),
                medicine,
                customerService.getCustomerById(request.getCustomerId())
        );

        purchaserRepository.save(purchaser);
    }

    public void updatePurchaser(final String id, final UpdatePurchaserRequest request) {
        Medicine medicine = medicineService.getMedicineById(request.getMedicineId());
        Purchaser purchaser = getPurchaserById(id);

        Purchaser updatedPurchaser = new Purchaser(
                purchaser.getId(),
                medicine.getPrice(),
                purchaser.getDate(),
                medicine,
                customerService.getCustomerById(request.getCustomerId()),
                purchaser.getSales()
        );

        purchaserRepository.save(updatedPurchaser);
    }

    public void deletePurchaser(final String id) {
        purchaserRepository.delete(getPurchaserById(id));
    }

    public PurchaserDto findPurchaserById(final String id) {
        return converter.convert(getPurchaserById(id));
    }

    public List<PurchaserDto> findAllPurchasers() {
        List<Purchaser> purchasers = purchaserRepository.findAll();

        if (purchasers.isEmpty()) {
            throw new PurchaserListEmptyException("Purchaser list is empty.");
        }

        return converter.convert(purchasers);
    }

    protected Purchaser getPurchaserById(final String id) {
        return purchaserRepository.findById(id)
                .orElseThrow(() -> new PurchaserNotFoundException("Purchaser not found by id:" + id));
    }
}
