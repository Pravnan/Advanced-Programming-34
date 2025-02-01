package com.example.demo1.repository;
import com.example.demo1.model.Customer;
import java.util.List;

public interface CustomerRepository {
    void save(Customer customer); // Create
    Customer findById(int customerId); // Read
    List<Customer> findAll(); // Read All
    void update(Customer customer); // Update
    void delete(int customerId); // Delete
}