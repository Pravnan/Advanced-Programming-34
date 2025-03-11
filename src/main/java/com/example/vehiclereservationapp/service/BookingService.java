package com.example.vehiclereservationapp.service;

import com.example.vehiclereservationapp.model.Booking;
import com.example.vehiclereservationapp.repository.BookingRepoImpl;
import com.example.vehiclereservationapp.repository.BookingRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService() {
        this.bookingRepository = new BookingRepoImpl();
    }

    // CRUD operations
    public void addBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public Booking getBookingByID(int id) {
        return bookingRepository.findById(id);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void updateBooking(Booking booking) {
        bookingRepository.update(booking);
    }

    public void removeBooking(int id) {
        bookingRepository.delete(id);
    }

    // Assign car and driver to a booking
    public boolean assignCarAndDriver(int bookingID, int carID, int driverID) {
        return bookingRepository.assignCarAndDriver(bookingID, carID, driverID);
    }

    public List<Booking> getUnassignedBookings() {
        return bookingRepository.getUnassignedBookings();
    }
    public List<Booking> getBookingslistByCustomerID(int customerID) {
        return bookingRepository.getBookingslistByCustomerID(customerID);
    }


}
