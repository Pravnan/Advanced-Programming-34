package com.example.vehiclereservationapp.service;

import com.example.vehiclereservationapp.model.Payment;
import com.example.vehiclereservationapp.repository.PaymentRepoImple;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class PaymentService {

    private final PaymentRepoImple paymentRepo;

    public PaymentService() {
        this.paymentRepo = new PaymentRepoImple();
    }

    public Payment findPaymentById(int id) {
        return paymentRepo.findById(id);
    }

    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }

    public boolean processPayment(int bookingID, BigDecimal amount, String paymentMethod, String paymentReference) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid payment amount.");
            return false;
        }

        Payment payment = new Payment();
        payment.setBookingID(bookingID);
        payment.setPaymentDate(new Timestamp(System.currentTimeMillis()));
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentReference(paymentReference);

        paymentRepo.save(payment);
        return true;
    }

    public void updatePayment(Payment payment) {
        paymentRepo.update(payment);
    }

    public void deletePayment(int paymentID) {
        Payment payment = paymentRepo.findById(paymentID);
        if (payment != null) {
            paymentRepo.delete(payment);
        }
    }
}
