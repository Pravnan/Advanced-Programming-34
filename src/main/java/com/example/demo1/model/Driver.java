package com.example.demo1.model;

public class Driver {
    private int driverID;
    private String name;

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAssignedCarID() {
        return assignedCarID;
    }

    public void setAssignedCarID(Integer assignedCarID) {
        this.assignedCarID = assignedCarID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String licenseNumber;
    private String phoneNumber;
    private Integer assignedCarID;
    private String status;
}
