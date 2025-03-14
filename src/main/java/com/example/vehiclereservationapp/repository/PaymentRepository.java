package com.example.vehiclereservationapp.repository;


import com.example.vehiclereservationapp.model.Payment;

import java.util.List;

public interface PaymentRepository {
    //CRUD
    Payment findById(int id);
    List<Payment> findAll();
    void save(Payment payment);
    void delete(Payment payment);
    void update(Payment payment);
}
