package com.example.demo1.service;

import com.example.demo1.model.Customer;
import com.example.demo1.repository.CustomerRepository;
import com.example.demo1.repository.CustomerRepoImple;
import java.util.List;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepoImple();
    }

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
}
