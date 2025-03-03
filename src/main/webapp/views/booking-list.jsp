<%@ page import="com.example.vehiclereservationapp.model.Booking" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Mirunan
  Date: 2025-02-21
  Time: 11:28 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking List</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Booking List</h1>

<%
    List<Booking> bookingList = (List<Booking>) request.getAttribute("bookings");
    if (bookingList != null && !bookingList.isEmpty()) {
%>
<h2>Bookings:</h2>
<table border="1">
    <tr>
        <th>Booking ID</th>
        <th>Customer ID</th>
        <th>Destination</th>
        <th>Booking Date</th>
        <th>Requested Time</th>
        <th>Total Amount</th>
        <th>Payment Status</th>
        <th>Pickup Location</th>
        <th>Dropoff Location</th>
        <th>Actions</th>
    </tr>
    <%
        for (Booking booking : bookingList) {
    %>
    <tr>
        <td><%= booking.getBookingID() %></td>
        <td><%= booking.getCustomerID() %></td>
        <td><%= booking.getDestination() %></td>
        <td><%= booking.getBookingDate() %></td>
        <td><%= booking.getRequestedTime() %></td>
        <td><%= booking.getTotalAmount() %></td>
        <td><%= booking.getPaymentStatus() %></td>
        <td><%= booking.getPickupLocation() %></td>
        <td><%= booking.getDropoffLocation() %></td>
        <td>
            <a href="booking?action=view&id=<%= booking.getBookingID() %>">View</a>
            <a href="booking?action=edit&id=<%= booking.getBookingID() %>">Edit</a>
            <a href="booking?action=delete&id=<%= booking.getBookingID() %>">Delete</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
} else {
%>
<p>No bookings found.</p>
<%
    }
%>

<a href="booking?action=add">Add New Booking</a>
</body>
</html>