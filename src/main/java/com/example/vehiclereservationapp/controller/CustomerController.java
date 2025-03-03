package com.example.vehiclereservationapp.controller;

import com.example.vehiclereservationapp.model.Customer;
import com.example.vehiclereservationapp.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/customer")
public class CustomerController extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService(); // Initialize CustomerService
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null || action.isEmpty()) {
                response.sendRedirect("customer?action=list");
                return;
            }

            switch (action) {
                case "list":
                    List<Customer> customers = customerService.getAllCustomers();
                    System.out.println("[INFO] Customers fetched: " + customers.size());
                    request.setAttribute("customers", customers);
                    request.getRequestDispatcher("/views/customer-list.jsp").forward(request, response);
                    break;

                case "view":
                    handleViewCustomer(request, response);
                    break;

                case "edit":
                    handleEditCustomer(request, response);
                    break;

                case "add":
                    request.getRequestDispatcher("/views/customer-add.jsp").forward(request, response);
                    break;

                default:
                    response.sendRedirect("customer?action=list");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null || action.isEmpty()) {
                response.sendRedirect("customer?action=list");
                return;
            }

            switch (action) {
                case "add":
                    handleAddCustomer(request, response);
                    break;

                case "update":
                    handleUpdateCustomer(request, response);
                    break;

                case "delete":
                    handleDeleteCustomer(request, response);
                    break;

                default:
                    response.sendRedirect("customer?action=list");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
        }
    }

    // ✅ View a customer by ID
    private void handleViewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Customer customer = customerService.getCustomerById(id);
            if (customer != null) {
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/views/customer-view.jsp").forward(request, response);
            } else {
                response.sendRedirect("customer?action=list");
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid customer ID format.");
            response.sendRedirect("customer?action=list");
        }
    }

    // ✅ Edit customer
    private void handleEditCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Customer customer = customerService.getCustomerById(id);
            if (customer != null) {
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/views/customer-edit.jsp").forward(request, response);
            } else {
                response.sendRedirect("customer?action=list");
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid customer ID format.");
            response.sendRedirect("customer?action=list");
        }
    }

    // ✅ Add customer
    private void handleAddCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String nic = request.getParameter("nic");
            String phoneNumber = request.getParameter("phoneNumber");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            if (!password.equals(confirmPassword)) {
                System.out.println("[ERROR] Passwords do not match.");
                response.sendRedirect("customer?action=add&error=password_mismatch");
                return;
            }

            // Create new Customer object
            Customer customer = new Customer(
                    0, name, email, address, nic, phoneNumber,
                    new Timestamp(System.currentTimeMillis()),
                    username, password, "CUSTOMER", true, 0, false, null
            );

            customerService.addCustomer(customer);
            System.out.println("[INFO] Customer registered successfully: " + username);

            response.sendRedirect("index.jsp?message=registered");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("customer?action=add&error=true");
        }
    }

    // ✅ Update customer
    private void handleUpdateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Customer customer = createCustomerFromRequest(request);
            customer.setCustomerID(id);
            customerService.updateCustomer(customer);
            System.out.println("[INFO] Customer updated: " + customer.getUsername());
            response.sendRedirect("customer?action=list");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid customer ID format.");
            response.sendRedirect("customer?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("customer?action=edit&id=" + request.getParameter("id") + "&error=true");
        }
    }

    // ✅ Delete customer
    private void handleDeleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            customerService.removeCustomer(id);
            System.out.println("[INFO] Customer deleted with ID: " + id);
            response.sendRedirect("customer?action=list");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid customer ID format.");
            response.sendRedirect("customer?action=list");
        }
    }

    // ✅ Helper method to create a Customer object from request parameters
    private Customer createCustomerFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        String phoneNumber = request.getParameter("phoneNumber");
        String username = request.getParameter("username");
        String passwordHash = request.getParameter("passwordHash");
        String role = request.getParameter("role");
        boolean isVerified = Boolean.parseBoolean(request.getParameter("isVerified"));

        if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] Name and Email are required fields!");
        }

        return new Customer(
                0, // ID will be auto-generated by the database
                name,
                email,
                address,
                nic,
                phoneNumber,
                null, // CreatedAt (handled in service layer)
                username,
                passwordHash,
                role,
                isVerified,
                0, // FailedAttempts (default 0)
                false, // IsLocked (default false)
                null // LastLogin (handled in service layer)
        );
    }
}
