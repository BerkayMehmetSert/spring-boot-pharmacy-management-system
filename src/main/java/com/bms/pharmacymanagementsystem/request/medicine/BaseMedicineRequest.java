package com.bms.pharmacymanagementsystem.request.medicine;
public class BaseMedicineRequest {
    private String name;
    private String description;
    private Double price;
    private String categoryId;


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategoryId() {
        return categoryId;
    }
}
