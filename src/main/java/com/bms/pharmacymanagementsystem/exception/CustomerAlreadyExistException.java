package com.bms.pharmacymanagementsystem.exception;

public class CustomerAlreadyExistException extends RuntimeException {
    public CustomerAlreadyExistException(String s) {
        super(s);
    }
}
