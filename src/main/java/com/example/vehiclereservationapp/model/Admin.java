package com.example.vehiclereservationapp.model;

import java.sql.Timestamp;

public class Admin {
    private int adminID;
    private String username;
    private String passwordHash;
    private String role;
    private String email;
    private boolean isVerified;
    private int failedAttempts;
    private boolean isLocked;
    private Timestamp lastLogin;

    // ✅ Constructor with all fields
    public Admin(int adminID, String username, String passwordHash, String role, String email, boolean isVerified, int failedAttempts, boolean isLocked, Timestamp lastLogin) {
        this.adminID = adminID;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.email = email;
        this.isVerified = isVerified;
        this.failedAttempts = failedAttempts;
        this.isLocked = isLocked;
        this.lastLogin = lastLogin;
    }

    // ✅ Constructor without optional fields
    public Admin(String username, String passwordHash, String role,String email) {
        this(0, username, passwordHash, role, email, false, 0, false, null);
    }

    // ✅ Getters and Setters
    public int getAdminID() { return adminID; }
    public void setAdminID(int adminID) { this.adminID = adminID; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { isVerified = verified; }

    public int getFailedAttempts() { return failedAttempts; }
    public void setFailedAttempts(int failedAttempts) { this.failedAttempts = failedAttempts; }

    public boolean isAccountLocked() { return isLocked; }
    public void setAccountLocked(boolean locked) { isLocked = locked; }

    public Timestamp getLastLogin() { return lastLogin; }
    public void setLastLogin(Timestamp lastLogin) { this.lastLogin = lastLogin; }
}
