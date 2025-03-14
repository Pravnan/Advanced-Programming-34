package com.example.vehiclereservationapp.controller;

import com.example.vehiclereservationapp.model.Payment;
import com.example.vehiclereservationapp.service.PaymentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/payment")
public class PaymentController extends HttpServlet {
    private PaymentService paymentService;

    @Override
    public void init() {
        this.paymentService = new PaymentService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Default action
        }

        switch (action) {
            case "list":
                listPayments(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deletePayment(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
        }
    }

    private void listPayments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Payment> payments = paymentService.getAllPayments();
        request.setAttribute("payments", payments);
        request.getRequestDispatcher("/views/payment-list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/payment-add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int paymentID = Integer.parseInt(request.getParameter("id"));
        Payment payment = paymentService.findPaymentById(paymentID);
        request.setAttribute("payment", payment);
        request.getRequestDispatcher("/views/payment-edit.jsp").forward(request, response);
    }

    private void deletePayment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int paymentID = Integer.parseInt(request.getParameter("id"));
        paymentService.deletePayment(paymentID);
        response.sendRedirect("payment?action=list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        if ("save".equals(action)) {
            savePayment(request, response);
        } else if ("update".equals(action)) {
            updatePayment(request, response);
        }
    }

    private void savePayment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        String paymentMethod = request.getParameter("paymentMethod");
        String paymentReference = request.getParameter("paymentReference");

        boolean success = paymentService.processPayment(bookingID, amount, paymentMethod, paymentReference);

        if (success) {
            response.sendRedirect("payment?action=list");
        } else {
            request.setAttribute("errorMessage", "Invalid payment details.");
            request.getRequestDispatcher("/views/payment-add.jsp").forward(request, response);
        }
    }

    private void updatePayment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int paymentID = Integer.parseInt(request.getParameter("paymentID"));
        int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        String paymentMethod = request.getParameter("paymentMethod");
        String paymentReference = request.getParameter("paymentReference");

        Payment payment = new Payment();
        payment.setPaymentID(paymentID);
        payment.setBookingID(bookingID);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentReference(paymentReference);

        paymentService.updatePayment(payment);
        response.sendRedirect("payment?action=list");
    }
}
