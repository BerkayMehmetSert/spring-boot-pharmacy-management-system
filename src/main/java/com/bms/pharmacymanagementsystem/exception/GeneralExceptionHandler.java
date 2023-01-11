package com.bms.pharmacymanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CategoryAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleCategoryAlreadyExistException(CategoryAlreadyExistException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(CategoryListEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleCategoryListEmptyException(CategoryListEmptyException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(CustomerAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleCustomerAlreadyExistException(CustomerAlreadyExistException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleCustomerNotFoundException(CategoryNotFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(CustomerListEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleCustomerListEmptyException(CategoryListEmptyException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(MedicineAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleMedicineAlreadyExistException(MedicineAlreadyExistException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(MedicineNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleMedicineNotFoundException(MedicineNotFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(MedicineListEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleMedicineListEmptyException(MedicineListEmptyException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(PharmacistAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handlePharmacistAlreadyExistException(PharmacistAlreadyExistException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(PharmacistNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handlePharmacistNotFoundException(PharmacistNotFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(PharmacistListEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handlePharmacistListEmptyException(PharmacistListEmptyException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(PurchaserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handlePurchaserNotFoundException(PurchaserNotFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(PurchaserListEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handlePurchaserListEmptyException(PurchaserListEmptyException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(ReportNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleReportNotFoundException(ReportNotFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(ReportListEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleReportListEmptyException(ReportListEmptyException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(SaleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleSaleNotFoundException(SaleNotFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }

    @ExceptionHandler(SaleListEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleSaleListEmptyException(SaleListEmptyException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }
}
