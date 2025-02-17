package com.example.vehiclereservationapp.repository;

import com.example.vehiclereservationapp.model.Admin;

import java.util.List;

public interface AdminRepository {
    void save(Admin admin); // Create
    Admin findById(int adminId); // Read
    List<Admin> findAll(); // Read All
    void update(Admin admin); // Update
    void delete(int adminId); // Delete




    Admin findByUsername(String username);
    void resetFailedAttempts(String username);
    void updateVerifiedStatus(String username, boolean isVerified);


    void updateFailedAttempts(int adminId, int failedAttempts);
    void lockAccount(int adminId);
    void unlockAccount(int adminId);
    void setEmailVerified(int adminId, boolean isVerified);
    void updateLastLogin(int adminId);
}


