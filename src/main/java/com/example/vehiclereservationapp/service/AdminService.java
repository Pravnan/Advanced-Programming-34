package com.example.vehiclereservationapp.service;

import com.example.vehiclereservationapp.model.Admin;
import com.example.vehiclereservationapp.repository.AdminRepoImple;
import com.example.vehiclereservationapp.repository.AdminRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService() {
        this.adminRepository = new AdminRepoImple();
    }

    //CRUD
    public void addAdmin(Admin admin) {
        // Hash password before saving
        String hashedPassword = hashPassword(admin.getPasswordHash());
        admin.setPasswordHash(hashedPassword);
        adminRepository.save(admin);
    }

    public Admin getAdminById(int adminId) {
        return adminRepository.findById(adminId);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public void updateAdmin(Admin admin) {
        // Hash password before updating (only if changed)
        if (admin.getPasswordHash() != null && !admin.getPasswordHash().isEmpty()) {
            String hashedPassword = hashPassword(admin.getPasswordHash());
            admin.setPasswordHash(hashedPassword);
        }
        adminRepository.update(admin);
    }

    public void removeAdmin(int adminId) {
        adminRepository.delete(adminId);
    }




    public boolean authenticateAdmin(String username, String password) {
        Admin admin = getAdminByUsername(username);
        if (admin == null) {
            return false; // Admin not found
        }

        // Check if the account is locked
        if (admin.isAccountLocked()) {
            System.out.println("Account is locked due to multiple failed login attempts.");
            return false;
        }

        // Verify password
        if (verifyPassword(password, admin.getPasswordHash())) {
            // Reset failed attempts on successful login
            admin.setFailedAttempts(0);
            admin.setLastLogin(new Timestamp(System.currentTimeMillis()));
            adminRepository.update(admin);
            return true;
        } else {
            // Increment failed attempts
            admin.setFailedAttempts(admin.getFailedAttempts() + 1);
            if (admin.getFailedAttempts() >= 3) {  // Lock account after 3 failed attempts
                admin.setAccountLocked(true);
            }
            adminRepository.update(admin);
            return false;
        }
    }



    public Admin getAdminByUsername(String username) {
        List<Admin> admins = getAllAdmins();
        for (Admin admin : admins) {
            if (admin.getUsername().equalsIgnoreCase(username)) {
                return admin;
            }
        }
        return null; // Not found
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

    // 🚀 New: Verify Hashed Password
    private boolean verifyPassword(String inputPassword, String storedHash) {
        return hashPassword(inputPassword).equals(storedHash);
    }

}
