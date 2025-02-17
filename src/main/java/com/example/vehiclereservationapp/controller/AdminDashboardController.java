package com.example.vehiclereservationapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminDashboardController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInAdmin") == null) {
            response.sendRedirect("/login"); // Redirect to login if not authenticated
            return;
        }

        // Forward to admin dashboard JSP
        request.getRequestDispatcher("/views/adminDashboard.jsp").forward(request, response);
    }
}
