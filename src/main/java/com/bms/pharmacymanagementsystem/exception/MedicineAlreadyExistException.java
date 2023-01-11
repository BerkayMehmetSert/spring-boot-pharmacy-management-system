package com.bms.pharmacymanagementsystem.exception;

public class MedicineAlreadyExistException extends RuntimeException {
    public MedicineAlreadyExistException(String s) {
        super(s);
    }
}
