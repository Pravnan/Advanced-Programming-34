package com.example.vehiclereservationapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/customer/dashboard")
public class CustomerDashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Ensure user is logged in as a Customer
        if (session == null || session.getAttribute("loggedInCustomer") == null) {
            response.sendRedirect("/login"); // Redirect to login if not authenticated
            System.out.println("From Customer Dashboard "+session.getAttribute("loggedInCustomer"));
            return;
        }

        // Forward to the Customer Dashboard JSP
        request.getRequestDispatcher("/views/customerDashboard.jsp").forward(request, response);
    }
}
