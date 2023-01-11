package com.bms.pharmacymanagementsystem.exception;

public class MedicineNotFoundException extends RuntimeException {
    public MedicineNotFoundException(String s) {
        super(s);
    }
}
