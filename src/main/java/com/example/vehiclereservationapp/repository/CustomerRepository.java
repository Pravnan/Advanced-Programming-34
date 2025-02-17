package com.example.vehiclereservationapp.repository;

import com.example.vehiclereservationapp.model.Customer;

import java.util.List;

public interface CustomerRepository {
    void save(Customer customer); // Create
    Customer findById(int customerId); // Read
    List<Customer> findAll(); // Read All
    void update(Customer customer); // Update
    void delete(int customerId); // Delete





    Customer findByUsername(String username);
    void resetFailedAttempts(String username);
    void updateVerifiedStatus(String username, boolean isVerified);


    void updateFailedAttempts(int customerId, int failedAttempts);
    void lockAccount(int customerId);
    void unlockAccount(int customerId);
    void setEmailVerified(int customerId, boolean isVerified);
    void updateLastLogin(int customerId);

}