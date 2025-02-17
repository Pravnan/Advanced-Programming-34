package com.example.vehiclereservationapp.controller;

import com.example.vehiclereservationapp.model.Admin;
import com.example.vehiclereservationapp.service.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminController extends HttpServlet {

    private AdminService adminService;

    @Override
    public void init() throws ServletException {
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("list".equals(action)) {
                List<Admin> admins = adminService.getAllAdmins();
                System.out.println("Admins fetched: " + admins.size());
                request.setAttribute("admins", admins);
                request.getRequestDispatcher("/views/admin-list.jsp").forward(request, response);
            }
            else if ("view".equals(action)) {
                int id = parseIntSafe(request.getParameter("id"));
                Admin admin = adminService.getAdminById(id);
                if (admin != null) {
                    request.setAttribute("admin", admin);
                    request.getRequestDispatcher("/views/admin-view.jsp").forward(request, response);
                } else {
                    response.sendRedirect("admin?action=list");
                }
            }
            else if ("edit".equals(action)) {
                int id = parseIntSafe(request.getParameter("id"));
                Admin admin = adminService.getAdminById(id);
                if (admin != null) {
                    request.setAttribute("admin", admin);
                    request.getRequestDispatcher("/views/admin-edit.jsp").forward(request, response);
                } else {
                    response.sendRedirect("admin?action=list");
                }
            }
            else if ("add".equals(action)) {
                request.getRequestDispatcher("/views/admin-add.jsp").forward(request, response);
            }
            else {
                response.sendRedirect("admin?action=list");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                Admin admin = createAdminFromRequest(request);
                System.out.println("Admin added: " + admin.getPasswordHash());
                adminService.addAdmin(admin);

                response.sendRedirect("admin?action=list");
            }
            else if ("update".equals(action)) {
                int id = parseIntSafe(request.getParameter("id"));
                Admin admin = createAdminFromRequest(request);
                admin.setAdminID(id);
                adminService.updateAdmin(admin);
                response.sendRedirect("admin?action=list");
            }
            else if ("delete".equals(action)) {
                int id = parseIntSafe(request.getParameter("id"));
                adminService.removeAdmin(id);
                response.sendRedirect("admin?action=list");
            }
            else {
                response.sendRedirect("admin?action=list");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin?action=list");
        }
    }

    private Admin createAdminFromRequest(HttpServletRequest request) {
        String username = request.getParameter("Username");
        String password = request.getParameter("Password"); // Fix parameter name

        String role = request.getParameter("role");
        String email = request.getParameter("email");
        return new Admin(username, password, role,email); // ID is auto-generated
    }

    // Helper method to safely parse integer values
    private int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0; // Default to 0 if invalid
        }
    }
}
