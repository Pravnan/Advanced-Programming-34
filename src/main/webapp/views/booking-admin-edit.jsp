<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.vehiclereservationapp.model.Admin" %>
<%@ page import="com.example.vehiclereservationapp.model.Booking" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    Admin admin = (Admin) session.getAttribute("loggedInAdmin");
    Booking booking = (Booking) request.getAttribute("booking");
    if (admin == null || booking == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Edit Booking (Admin)</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Edit Booking</h1>

<form action="booking?action=update-booking" method="post">
    <input type="hidden" name="id" value="<%= booking.getBookingID() %>">

    <!-- Booking Details (readonly for admin) -->
    <label for="customerID">Customer ID:</label>
    <input type="text" id="customerID" name="customerID" value="<%= booking.getCustomerID() %>" readonly><br><br>

    <label for="destination">Destination:</label>
    <input type="text" id="destination" name="destination" value="<%= booking.getDestination() %>" required><br><br>

    <label for="pickupLocation">Pickup Location:</label>
    <input type="text" id="pickupLocation" name="pickupLocation" value="<%= booking.getPickupLocation() %>" required><br><br>

    <label for="dropoffLocation">Dropoff Location:</label>
    <input type="text" id="dropoffLocation" name="dropoffLocation" value="<%= booking.getDropoffLocation() %>" required><br><br>

    <label for="requestedTime">Requested Time:</label>
    <input type="datetime-local" id="requestedTime" name="requestedTime" value="<%= booking.getRequestedTime() %>" required><br><br>

    <!-- Admin-specific Fields -->
    <label for="totalAmount">Total Amount:</label>
    <input type="text" id="totalAmount" name="totalAmount" value="<%= booking.getTotalAmount() != null ? booking.getTotalAmount().toString() : "" %>" required><br><br>

    <label for="paymentStatus">Payment Status:</label>
    <select id="paymentStatus" name="paymentStatus" required>
        <option value="PAID" <%= booking.getPaymentStatus().equals("PAID") ? "selected" : "" %>>PAID</option>
        <option value="PENDING" <%= booking.getPaymentStatus().equals("PENDING") ? "selected" : "" %>>PENDING</option>
        <option value="CANCELLED" <%= booking.getPaymentStatus().equals("CANCELLED") ? "selected" : "" %>>CANCELLED</option>
    </select><br><br>

    <!-- Submit Button -->
    <button type="submit">Update Booking</button>
</form>

<a href="booking?action=list">Back to Booking List</a>
</body>
</html>
