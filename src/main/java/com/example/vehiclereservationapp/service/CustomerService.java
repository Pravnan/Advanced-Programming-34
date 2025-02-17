package com.example.vehiclereservationapp.service;

import com.example.vehiclereservationapp.model.Customer;
import com.example.vehiclereservationapp.repository.CustomerRepoImple;
import com.example.vehiclereservationapp.repository.CustomerRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepoImple();
    }

    // CRUD
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer getCustomerById(int customerId) {
        return customerRepository.findById(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void updateCustomer(Customer customer) {
        customerRepository.update(customer);
    }

    public void removeCustomer(int customerId) {
        customerRepository.delete(customerId);
    }





    public boolean authenticateCustomer(String username, String password) {
        Customer customer = getCustomerByUsername(username);
        if (customer == null) {
            return false; // Customer not found
        }

        // Check if the account is locked
        if (customer.getFailedAttempts() >= 3) {
            System.out.println("Account is locked due to multiple failed login attempts.");
            return false;
        }

        // Verify password
        if (verifyPassword(password, customer.getPasswordHash())) {
            // Reset failed attempts on successful login
            customer.setFailedAttempts(0);
            customer.setLastLogin(new Timestamp(System.currentTimeMillis()));
            customerRepository.update(customer);
            return true;
        } else {
            // Increment failed attempts
            customer.setFailedAttempts(customer.getFailedAttempts() + 1);
            if (customer.getFailedAttempts() >= 3) {  // Lock account after 3 failed attempts
                System.out.println("Account locked due to multiple failed login attempts.");
            }
            return false;
        }
    }



    public Customer getCustomerByUsername(String username) {
        return customerRepository.findByUsername(username);
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


}
