package com.example.vehiclereservationapp.repository;

import com.example.vehiclereservationapp.model.Driver;

import java.util.List;

public interface DriverRepository {

    void save(Driver driver); // Create
    Driver findById(int driverId); // Read
    List<Driver> findAll(); // Read All
    void update(Driver driver); // Update
    void delete(int driverId); // Delete



    Driver findByUsername(String username);
    void resetFailedAttempts(String username);
    void updateVerifiedStatus(String username, boolean isVerified);

    void updateFailedAttempts(String username, int failedAttempts);
    void lockAccount(int driverId);
    void unlockAccount(int driverId);
    void setEmailVerified(int driverId, boolean isVerified);
    void updateLastLogin(int driverId);
}
