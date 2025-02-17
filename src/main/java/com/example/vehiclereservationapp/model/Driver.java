package com.example.vehiclereservationapp.model;

import java.sql.Timestamp;

public class Driver {
    private int driverID;
    private String name;
    private String licenseNumber;
    private String phoneNumber;
    private Integer assignedCarID;
    private String status;

    // New fields for login
    private String username;
    private String passwordHash;
    private String role; // For example: "DRIVER"
    private boolean isVerified; // For email verification
    private int failedAttempts; // For account lockout
    private boolean isLocked; // New field for account lock status
    private Timestamp lastLogin; // New field for tracking last login time

    // Constructor to initialize the Driver object
    public Driver(int driverID, String name, String licenseNumber, String phoneNumber, Integer assignedCarID,
                  String status, String username, String passwordHash, String role,
                  int failedAttempts, boolean isVerified, boolean isLocked, Timestamp lastLogin) {
        this.driverID = driverID;
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
        this.assignedCarID = assignedCarID;
        this.status = status;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.isVerified = isVerified;
        this.failedAttempts = failedAttempts;
        this.isLocked = isLocked;
        this.lastLogin = lastLogin;
    }

    // Getters and Setters for all the fields
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
}
