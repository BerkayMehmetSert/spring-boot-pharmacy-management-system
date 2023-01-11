package com.bms.pharmacymanagementsystem.request.report;

public class BaseReportRequest {
    private String customerId;
    private String purchaserId;
    private String saleId;

    public String getCustomerId() {
        return customerId;
    }

    public String getPurchaserId() {
        return purchaserId;
    }

    public String getSaleId() {
        return saleId;
    }
}
