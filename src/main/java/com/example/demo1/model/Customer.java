package com.example.demo1.model;

import java.sql.Timestamp;

public class Customer {

    public Customer(int customerID, String name, String email, String address, String nic, String phoneNumber, Timestamp createdAt) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.address = address;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
    }



    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    private int customerID;
    private String name;
    private String email;
    private String address;
    private String nic;
    private String phoneNumber;
    private Timestamp createdAt;
}
