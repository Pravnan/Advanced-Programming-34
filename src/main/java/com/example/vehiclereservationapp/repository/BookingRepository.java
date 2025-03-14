package com.example.vehiclereservationapp.repository;

import com.example.vehiclereservationapp.model.Booking;
import com.example.vehiclereservationapp.model.BookingCar;


import java.util.List;

public interface BookingRepository {


    //CRUD
    void save(Booking bookingID); // Create
    Booking findById(int bookingID); // Read
    List<Booking> findAll(); // Read All
    void update(Booking booking); // Update
    void delete(int bookingID); //

    //admin update booking
    void updateBooking(Booking booking);

    List<Booking> getBookingslistByCustomerID(int customerID);
    List<Booking> getUnassignedBookings();
    boolean assignCarAndDriver(int bookingID, int carID, int driverID);
}
