package com.example.vehiclereservationapp.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Booking {

    private int bookingID;
    private int customerID;
    private String destination;
    private Timestamp bookingDate;  // Date when the booking was created
    private Timestamp requestedTime;  // Time customer wants the booking
    private BigDecimal totalAmount;
    private String paymentStatus;
    private String pickupLocation;
    private String dropoffLocation;

    // Default constructor
    public Booking() {}

    // Constructor with essential fields
    public Booking(int customerID, String destination, Timestamp bookingDate,
                   BigDecimal totalAmount, String paymentStatus, String pickupLocation, String dropoffLocation, Timestamp requestedTime) {
        this.customerID = customerID;
        this.destination = destination;
        this.bookingDate = bookingDate;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.requestedTime = requestedTime;
    }



    // Getter and Setter Methods
    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Timestamp getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Timestamp bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Timestamp getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(Timestamp requestedTime) {
        this.requestedTime = requestedTime;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }
}
