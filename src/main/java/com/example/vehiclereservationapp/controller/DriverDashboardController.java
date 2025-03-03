package com.example.vehiclereservationapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/driver/dashboard")
public class DriverDashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Ensure user is logged in as a Driver
        if (session == null || session.getAttribute("loggedInDriver") == null) {
            response.sendRedirect("views/login.jsp"); // Redirect to login if not authenticated
            return;
        }

        // Forward to the Driver Dashboard JSP
        request.getRequestDispatcher("/views/driverDashboard.jsp").forward(request, response);
    }
}
