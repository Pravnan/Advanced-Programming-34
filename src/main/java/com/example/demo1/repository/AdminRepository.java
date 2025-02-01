package com.example.demo1.repository;

import com.example.demo1.model.Admin;

import java.util.List;

public interface AdminRepository {
    void save(Admin admin); // Create
    Admin findById(int adminId); // Read
    List<Admin> findAll(); // Read All
    void update(Admin admin); // Update
    void delete(int adminId); // Delete
}


