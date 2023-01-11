package com.bms.pharmacymanagementsystem.service;

import com.bms.pharmacymanagementsystem.dto.MedicineDto;
import com.bms.pharmacymanagementsystem.dto.converter.MedicineDtoConverter;
import com.bms.pharmacymanagementsystem.exception.MedicineAlreadyExistException;
import com.bms.pharmacymanagementsystem.exception.MedicineListEmptyException;
import com.bms.pharmacymanagementsystem.exception.MedicineNotFoundException;
import com.bms.pharmacymanagementsystem.model.Medicine;
import com.bms.pharmacymanagementsystem.repository.MedicineRepository;
import com.bms.pharmacymanagementsystem.request.medicine.CreateMedicineRequest;
import com.bms.pharmacymanagementsystem.request.medicine.UpdateMedicineRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {
    private final MedicineRepository medicineRepository;
    private final CategoryService categoryService;
    private final MedicineDtoConverter converter;

    public MedicineService(MedicineRepository medicineRepository,
                           CategoryService categoryService,
                           MedicineDtoConverter converter) {
        this.medicineRepository = medicineRepository;
        this.categoryService = categoryService;
        this.converter = converter;
    }

    public void createMedicine(final CreateMedicineRequest request) {
        checkIfMedicineExists(request.getName());

        Medicine medicine = new Medicine(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                categoryService.getCategoryById(request.getCategoryId())
        );

        medicineRepository.save(medicine);
    }

    public void updateMedicine(final String id, final UpdateMedicineRequest request) {
        Medicine medicine = getMedicineById(id);

        if (!medicine.getName().equals(request.getName())) {
            checkIfMedicineExists(request.getName());
        }

        Medicine updatedMedicine = new Medicine(
                medicine.getId(),
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                categoryService.getCategoryById(request.getCategoryId()),
                medicine.getPurchasers(),
                medicine.getSales()
        );

        medicineRepository.save(updatedMedicine);
    }

    public void updateMedicineCategoryByCategoryId(final String id, final String categoryId) {
        Medicine medicine = getMedicineById(id);
        Medicine updatedMedicine = new Medicine(
                medicine.getId(),
                medicine.getName(),
                medicine.getDescription(),
                medicine.getPrice(),
                categoryService.getCategoryById(categoryId),
                medicine.getPurchasers(),
                medicine.getSales()
        );

        medicineRepository.save(updatedMedicine);
    }

    public void deleteMedicine(final String id) {
        medicineRepository.delete(getMedicineById(id));
    }

    public MedicineDto findMedicineById(final String id) {
        return converter.convert(getMedicineById(id));
    }

    public List<MedicineDto> findAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();

        if (medicines.isEmpty()) {
            throw new MedicineListEmptyException("Medicine list is empty.");
        }

        return converter.convert(medicines);
    }

    protected Medicine getMedicineById(final String id) {
        return medicineRepository.findById(id)
                .orElseThrow(() -> new MedicineNotFoundException("Medicine not fount by id: " + id));
    }

    private void checkIfMedicineExists(final String name) {
        if (medicineRepository.existsByNameIgnoreCase(name)) {
            throw new MedicineAlreadyExistException("Medicine already exist by name: " + name);
        }
    }
}
