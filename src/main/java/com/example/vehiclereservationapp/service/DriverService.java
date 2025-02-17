package com.example.vehiclereservationapp.service;

import com.example.vehiclereservationapp.model.Driver;
import com.example.vehiclereservationapp.repository.DriverRepoImple;
import com.example.vehiclereservationapp.repository.DriverRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class DriverService {
    private final DriverRepository driverRepository;

    public DriverService() {
        this.driverRepository = new DriverRepoImple();
    }

    //CRUD
    public void addDriver(Driver driver) {
        // Hash password before saving
        String hashedPassword = hashPassword(driver.getPasswordHash());
        driver.setPasswordHash(hashedPassword);
        driverRepository.save(driver);
    }

    public Driver getDriverById(int driverId) {
        return driverRepository.findById(driverId);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public void updateDriver(Driver driver) {
        // Hash password before updating (only if changed)
        if (driver.getPasswordHash() != null && !driver.getPasswordHash().isEmpty()) {
            String hashedPassword = hashPassword(driver.getPasswordHash());
            driver.setPasswordHash(hashedPassword);
        }
        driverRepository.update(driver);
    }

    public void removeDriver(int driverId) {
        driverRepository.delete(driverId);
    }







    public boolean authenticateDriver(String username, String password) {
        Driver driver = getDriverByUsername(username);
        if (driver == null) {
            return false; // Driver not found
        }

        // Check if the account is locked
        if (driver.getFailedAttempts() >= 3) {
            System.out.println("Account is locked due to multiple failed login attempts.");
            return false;
        }

        // Verify password
        if (verifyPassword(password, driver.getPasswordHash())) {
            // Reset failed attempts on successful login
            resetFailedAttempts(driver.getUsername());
            driverRepository.updateLastLogin(driver.getDriverID());
            return true;
        } else {
            // Increment failed attempts
            int newFailedAttempts = driver.getFailedAttempts() + 1;
            updateFailedAttempts(username, newFailedAttempts);

            if (newFailedAttempts >= 3) {  // Lock account after 3 failed attempts
                System.out.println("Account locked due to multiple failed login attempts.");
            }
            return false;
        }
    }

    public Driver getDriverByUsername(String username) {
        return driverRepository.findByUsername(username);
    }

    public void updateFailedAttempts(String username, int failedAttempts) {
        driverRepository.updateFailedAttempts(username, failedAttempts);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    private boolean verifyPassword(String inputPassword, String storedHash) {
        return hashPassword(inputPassword).equals(storedHash);
    }

    public void resetFailedAttempts(String username) {
        driverRepository.resetFailedAttempts(username);
    }

    public void updateVerifiedStatus(String username, boolean isVerified) {
        driverRepository.updateVerifiedStatus(username, isVerified);
    }
}
