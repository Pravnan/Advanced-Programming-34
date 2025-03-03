package com.example.vehiclereservationapp.controller;

import com.example.vehiclereservationapp.model.Admin;
import com.example.vehiclereservationapp.model.Customer;
import com.example.vehiclereservationapp.model.Driver;
import com.example.vehiclereservationapp.service.AdminService;
import com.example.vehiclereservationapp.service.CustomerService;
import com.example.vehiclereservationapp.service.DriverService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private DriverService driverService;
    private AdminService adminService;
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        driverService = new DriverService(); // Initialize DriverService
        adminService = new AdminService(); // Initialize AdminService
        customerService = new CustomerService(); // Initialize CustomerService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the login page
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        Driver driver = driverService.getDriverByUsername(username);
        Admin admin = adminService.getAdminByUsername(username);
        Customer customer = customerService.getCustomerByUsername(username);

        // Check if the user is a Driver
        if (driver != null && driverService.authenticateDriver(driver.getUsername(), password)) {
            if (driver.getFailedAttempts() >= 3) {
                request.setAttribute("errorMessage", "Account locked due to multiple failed attempts.");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
                return;
            }
            if (!driver.isVerified()) {
                request.setAttribute("errorMessage", "Please verify your email address.");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
                return;
            }
            HttpSession session = request.getSession();
            session.setAttribute("loggedInDriver", driver);
            if ("on".equals(rememberMe)) {
                session.setMaxInactiveInterval(60 * 60 * 24 * 7); // Keep the session alive for 7 days if 'Remember Me' is checked
            }
            response.sendRedirect(request.getContextPath() + "/driver/dashboard"); // Redirect to the Driver dashboard

        }
        // Check if the user is an Admin
        else if (admin != null && adminService.authenticateAdmin(admin.getUsername(), password)) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInAdmin", admin);
            if ("on".equals(rememberMe)) {
                session.setMaxInactiveInterval(60 * 60 * 24 * 7);
            }
            response.sendRedirect(request.getContextPath() + "/admin/dashboard"); // Redirect to the Admin dashboard

        }
        // Check if the user is a Customer
        else if (customer != null && customerService.authenticateCustomer(customer.getUsername(), password)) {
            if (customer.getFailedAttempts() >= 3) {
                request.setAttribute("errorMessage", "Account locked due to multiple failed attempts.");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
                return;
            }
            if (!customer.isVerified()) {
                request.setAttribute("errorMessage", "Please verify your email address.");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
                return;
            }
            HttpSession session = request.getSession();
            session.setAttribute("loggedInCustomer", customer);
            if ("on".equals(rememberMe)) {
                session.setMaxInactiveInterval(60 * 60 * 24 * 7);
            }
            response.sendRedirect(request.getContextPath() + "/customer/dashboard"); // Redirect to the Customer dashboard
        }
        else {
            // If login fails, redirect to login page with error
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}