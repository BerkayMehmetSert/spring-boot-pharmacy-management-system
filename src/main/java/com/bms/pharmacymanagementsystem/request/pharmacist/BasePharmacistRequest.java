package com.bms.pharmacymanagementsystem.request.pharmacist;

public class BasePharmacistRequest {
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String address;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender(){
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
