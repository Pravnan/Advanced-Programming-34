<%@ page import="com.example.vehiclereservationapp.model.Booking" %><%--
  Created by IntelliJ IDEA.
  User: Mirunan
  Date: 2025-02-21
  Time: 11:29 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking Details</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Booking Details</h1>

<%
    Booking booking = (Booking) request.getAttribute("booking");
    if (booking != null) {
%>
<table border="1">
    <tr>
        <th>Booking ID</th>
        <td><%= booking.getBookingID() %></td>
    </tr>
    <tr>
        <th>Customer ID</th>
        <td><%= booking.getCustomerID() %></td>
    </tr>
    <tr>
        <th>Destination</th>
        <td><%= booking.getDestination() %></td>
    </tr>
    <tr>
        <th>Booking Date</th>
        <td><%= booking.getBookingDate() %></td>
    </tr>
    <tr>
        <th>Requested Time</th>
        <td><%= booking.getRequestedTime() %></td>
    </tr>
    <tr>
        <th>Total Amount</th>
        <td><%= booking.getTotalAmount() %></td>
    </tr>
    <tr>
        <th>Payment Status</th>
        <td><%= booking.getPaymentStatus() %></td>
    </tr>
    <tr>
        <th>Pickup Location</th>
        <td><%= booking.getPickupLocation() %></td>
    </tr>
    <tr>
        <th>Dropoff Location</th>
        <td><%= booking.getDropoffLocation() %></td>
    </tr>
</table>

<a href="booking?action=edit&id=<%= booking.getBookingID() %>">Edit</a>
<a href="booking?action=list">Back to List</a>

<%
} else {
%>
<p>Booking not found.</p>
<a href="booking?action=list">Back to List</a>
<%
    }
%>

</body>
</html>
