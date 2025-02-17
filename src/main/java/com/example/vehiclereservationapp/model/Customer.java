package com.example.vehiclereservationapp.model;

import java.sql.Timestamp;

public class Customer {
    private int customerID;
    private String name;
    private String email;
    private String address;
    private String nic;
    private String phoneNumber;
    private Timestamp createdAt;

    // New fields for login
    private String username;
    private String passwordHash;
    private String role; // e.g., "CUSTOMER"
    private boolean isVerified; // For email verification
    private int failedAttempts; // For account lockout
    private boolean isLocked; // Account lock status
    private Timestamp lastLogin; // Last login timestamp

    // ✅ Constructor with all fields
    public Customer(int customerID, String name, String email, String address, String nic, String phoneNumber, Timestamp createdAt,
                    String username, String passwordHash, String role, boolean isVerified, int failedAttempts, boolean isLocked, Timestamp lastLogin) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.address = address;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.isVerified = isVerified;
        this.failedAttempts = failedAttempts;
        this.isLocked = isLocked;
        this.lastLogin = lastLogin;
    }

    // ✅ Getters and Setters
    public int getCustomerID() { return customerID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getNic() { return nic; }
    public void setNic(String nic) { this.nic = nic; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { isVerified = verified; }

    public int getFailedAttempts() { return failedAttempts; }
    public void setFailedAttempts(int failedAttempts) { this.failedAttempts = failedAttempts; }

    public boolean isAccountLocked() { return isLocked; }
    public void setAccountLocked(boolean locked) { isLocked = locked; }

    public Timestamp getLastLogin() { return lastLogin; }
    public void setLastLogin(Timestamp lastLogin) { this.lastLogin = lastLogin; }
}
