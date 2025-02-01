package com.example.demo1.service;

import com.example.demo1.model.Admin;
import com.example.demo1.model.Customer;
import com.example.demo1.repository.AdminRepoImple;
import com.example.demo1.repository.AdminRepository;
import com.example.demo1.repository.CustomerRepoImple;

import java.util.List;

public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService() {
        this.adminRepository = new AdminRepoImple();
    }

    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public Admin getAdminById(int adminId) {
        return adminRepository.findById(adminId);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public void updateAdmin(Admin admin) {
        adminRepository.update(admin);
    }

    public void removeAdmin(int adminId) {
        adminRepository.delete(adminId);
    }
}
