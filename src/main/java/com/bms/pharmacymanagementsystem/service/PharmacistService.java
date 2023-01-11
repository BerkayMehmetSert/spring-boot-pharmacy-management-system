package com.bms.pharmacymanagementsystem.service;

import com.bms.pharmacymanagementsystem.dto.PharmacistDto;
import com.bms.pharmacymanagementsystem.dto.converter.PharmacistDtoConverter;
import com.bms.pharmacymanagementsystem.exception.PharmacistAlreadyExistException;
import com.bms.pharmacymanagementsystem.exception.PharmacistListEmptyException;
import com.bms.pharmacymanagementsystem.exception.PharmacistNotFoundException;
import com.bms.pharmacymanagementsystem.model.Pharmacist;
import com.bms.pharmacymanagementsystem.repository.PharmacistRepository;
import com.bms.pharmacymanagementsystem.request.pharmacist.CreatePharmacistRequest;
import com.bms.pharmacymanagementsystem.request.pharmacist.UpdatePharmacistRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacistService {
    private final PharmacistRepository pharmacistRepository;
    private final PharmacistDtoConverter converter;

    public PharmacistService(PharmacistRepository pharmacistRepository,
                             PharmacistDtoConverter converter) {
        this.pharmacistRepository = pharmacistRepository;
        this.converter = converter;
    }

    public void createPharmacist(final CreatePharmacistRequest request) {
        checkIfPharmacistExists(request.getEmail());

        Pharmacist pharmacist = new Pharmacist(
                request.getFirstName(),
                request.getLastName(),
                request.getGender(),
                request.getAge(),
                request.getAddress(),
                request.getEmail()
        );

        pharmacistRepository.save(pharmacist);
    }

    public void updatePharmacist(final String id, final UpdatePharmacistRequest request) {
        Pharmacist pharmacist = getPharmacistById(id);

        if (!pharmacist.getEmail().equals(request.getEmail())) {
            checkIfPharmacistExists(request.getEmail());
        }

        Pharmacist updatedPharmacist = new Pharmacist(
                pharmacist.getId(),
                request.getFirstName(),
                request.getLastName(),
                request.getGender(),
                request.getAge(),
                request.getAddress(),
                request.getEmail(),
                pharmacist.getSales()
        );

        pharmacistRepository.save(updatedPharmacist);
    }

    public void deletePharmacist(final String id) {
        pharmacistRepository.delete(getPharmacistById(id));
    }

    public PharmacistDto findPharmacistById(final String id) {
        return converter.convert(getPharmacistById(id));
    }

    public List<PharmacistDto> findAllPharmacists() {
        List<Pharmacist> pharmacists = pharmacistRepository.findAll();

        if (pharmacists.isEmpty()) {
            throw new PharmacistListEmptyException("Pharmacist list is empty.");
        }

        return converter.convert(pharmacists);
    }

    protected Pharmacist getPharmacistById(final String id) {
        return pharmacistRepository.findById(id)
                .orElseThrow(() -> new PharmacistNotFoundException("Pharmacist not found by id:" + id));
    }

    private void checkIfPharmacistExists(final String email) {
        if (pharmacistRepository.existsByEmail(email)) {
            throw new PharmacistAlreadyExistException("Pharmacist already exist by email: " + email);
        }
    }
}
