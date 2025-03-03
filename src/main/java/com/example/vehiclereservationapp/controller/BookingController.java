package com.example.vehiclereservationapp.controller;

import com.example.vehiclereservationapp.model.Admin;
import com.example.vehiclereservationapp.model.Booking;
import com.example.vehiclereservationapp.model.Customer;
import com.example.vehiclereservationapp.service.BookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/booking")
public class BookingController extends HttpServlet {

    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        bookingService = new BookingService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Get logged-in admin or customer from session
        HttpSession session = request.getSession();
        Admin loggedInAdmin = (Admin) session.getAttribute("loggedInAdmin");
        Customer loggedInCustomer = (Customer) session.getAttribute("loggedInCustomer");


        switch (action != null ? action : "list") {
            case "list":
                if (loggedInAdmin != null) {
                    // Admin: Fetch all bookings
                    List<Booking> allBookings = bookingService.getAllBookings();
                    request.setAttribute("bookings", allBookings);
                    request.getRequestDispatcher("/views/booking-list.jsp").forward(request, response);
                } else if (loggedInCustomer != null) {
                    // Customer: Fetch bookings specific to the logged-in customer
                    List<Booking> customerBookings = bookingService.getBookingslistByCustomerID(loggedInCustomer.getCustomerID());
                    request.setAttribute("bookings", customerBookings);
                    request.getRequestDispatcher("/views/booking-list.jsp").forward(request, response);
                } else {
                    response.sendRedirect("login");  // Redirect to login if no user is logged in
                }
                break;

            case "view":
                handleViewBooking(request, response);
                break;

            case "edit":
                handleEditBooking(request, response);
                break;

            case "add":
                request.getRequestDispatcher("/views/booking-add.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect("booking?action=list");
        }
    }

    private void handleEditBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            System.out.println(request.getParameter("id"));
            int bookingID = Integer.parseInt(request.getParameter("id"));
            Booking booking = bookingService.getBookingByID(bookingID);
            if (booking != null) {
                request.setAttribute("booking", booking);
                request.getRequestDispatcher("/views/booking-edit.jsp").forward(request, response);
            }else{
                System.out.println("Redirected through here");
                response.sendRedirect("booking?action=list");
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid booking ID format.");
            response.sendRedirect("booking?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Debugging: Print all request parameters
        System.out.println("Received POST request:");
        request.getParameterMap().forEach((key, value) ->
                System.out.println(key + ": " + String.join(", ", value))
        );

        switch (action != null ? action : "list") {
            case "add":
                Booking newBooking = createCustomerBookingFromRequest(request);
                bookingService.addBooking(newBooking);
                response.sendRedirect("booking?action=list");
                break;

            case "assign":
                int bookingID = Integer.parseInt(request.getParameter("bookingID"));
                int carID = Integer.parseInt(request.getParameter("carID"));
                int driverID = Integer.parseInt(request.getParameter("driverID"));
                bookingService.assignCarAndDriver(bookingID, carID, driverID);
                response.sendRedirect("booking?action=list");
                break;

            case "update":
                handleUpdateBooking(request, response);
                break;
            case "delete":
                handleDeleteBooking(request, response);
                break;
            default:
                response.sendRedirect("booking?action=list");
        }
    }

    private void handleDeleteBooking(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            int bookingID = Integer.parseInt(request.getParameter("id"));
            bookingService.removeBooking(bookingID);
            System.out.println("[INFO] booking deleted with ID: " + bookingID);
            response.sendRedirect("booking?action=list");
        }catch (NumberFormatException e){
            System.out.println("[ERROR] Invalid booking ID format.");
            response.sendRedirect("booking?action=list");
        }
    }

    private void handleUpdateBooking(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int customerID = Integer.parseInt(request.getParameter("customerID"));
            Booking booking = createCustomerBookingFromRequest(request);
            booking.setBookingID(id);
            booking.setCustomerID(customerID);
            bookingService.updateBooking(booking);
            System.out.println("[INFO] booking Destination updated: " + booking.getDestination());
            response.sendRedirect("booking?action=list");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid booking ID format. handleUpdateBooking");
            response.sendRedirect("booking?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("booking?action=edit&id=" + request.getParameter("id") + "&error=true");
        }
    }

    private void handleViewBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Booking booking = bookingService.getBookingByID(id);
        if (booking != null) {
            request.setAttribute("booking", booking);
            request.getRequestDispatcher("/views/booking-view.jsp").forward(request, response);
        } else {
            response.sendRedirect("booking?action=list");
        }
    }

    private Booking createCustomerBookingFromRequest(HttpServletRequest request) {
        String customerID = request.getParameter("customerID");
        String destination = request.getParameter("destination");
        String pickupLocation = request.getParameter("pickupLocation");
        String dropoffLocation = request.getParameter("dropoffLocation");
        String requestedTime = request.getParameter("requestedTime");

        // Validate required fields
        if (customerID == null || customerID.isEmpty() ||
                destination == null || destination.isEmpty() ||
                pickupLocation == null || pickupLocation.isEmpty() ||
                dropoffLocation == null || dropoffLocation.isEmpty() ||
                requestedTime == null || requestedTime.isEmpty()) {
            throw new IllegalArgumentException("Missing required booking details.");
        }

        // Convert and validate timestamps
        Timestamp requestedTimestamp;

        try {
            // Convert "2025-03-02T20:40" → "2025-03-02 20:40:00"
            requestedTimestamp = Timestamp.valueOf(requestedTime.replace("T", " ") + ":00");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid requested time format: " + requestedTime);
        }

        return new Booking(
                Integer.parseInt(customerID),
                destination,
                null,// Sent by DB diretly
                null, // Initially null, can be updated later
                "Pending",
                pickupLocation,
                dropoffLocation,
                requestedTimestamp
        );
    }


}
