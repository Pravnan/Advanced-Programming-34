package com.example.demo1.model;

import java.sql.Timestamp;

public class Admin {

    public Admin(int adminID, String username, String passwordHash, String role) {
        this.adminID = adminID;
        this.username = username;
        this.role = role;
        this.passwordHash = passwordHash;
    }



    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
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

    private int adminID;
    private String username;
    private String passwordHash;
    private String role;
}
