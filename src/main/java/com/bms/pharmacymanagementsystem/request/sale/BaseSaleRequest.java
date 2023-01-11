package com.bms.pharmacymanagementsystem.request.sale;

public class BaseSaleRequest {
    private Integer count;
    private String customerId;
    private String pharmacistId;
    private String medicineId;
    private String purchaserId;

    public Integer getCount() {
        return count;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPharmacistId() {
        return pharmacistId;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public String getPurchaserId() {
        return purchaserId;
    }
}
