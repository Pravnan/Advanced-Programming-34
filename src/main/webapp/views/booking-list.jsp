<%@ page import="com.example.vehiclereservationapp.model.Booking" %>
<%@ page import="com.example.vehiclereservationapp.model.Admin" %> <!-- Import Admin class -->
<%@ page import="com.example.vehiclereservationapp.model.Customer" %> <!-- Import Customer class -->
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking List</title>
    <link rel="stylesheet" href="/styles.css">

    <!-- Optional inline style for quick testing -->
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f7f9fc;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1 {
            color: #333333; /* Green color */
            margin-top: 40px;
        }

        .container {
            max-width: 1600px;
            margin: 0 auto;
            padding: 40px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,0.05);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            font-size: 16px;
        }

        th {
            background-color: #4CAF50;
            color: #fff;
            font-weight: 600;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #e6f7ff;
        }

        a {
            text-decoration: none;
            color: #4CAF50;
            font-weight: 500;
            margin-right: 8px;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #ffffff;
        }

        .btn-add {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            padding: 12px 24px;
            margin-top: 30px;
            text-align: center;
            border-radius: 5px;
            font-size: 16px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .btn-add:hover {
            background-color: #3b783d;
        }

        .actions a {
            display: inline-block;
            margin-right: 8px;
            padding: 8px 16px;
            background-color: #f1f1f1;
            border-radius: 4px;
            transition: background-color 0.2s ease;
        }

        .actions a:hover {
            background-color: #ddd;
        }

        p {
            color: #888;
        }
    </style>
</head>
<body>

<div class="container">


    <%
        List<Booking> bookingList = (List<Booking>) request.getAttribute("bookings");
        Admin loggedInAdmin = (Admin) session.getAttribute("loggedInAdmin");
        Customer loggedInCustomer = (Customer) session.getAttribute("loggedInCustomer");

        if (bookingList != null && !bookingList.isEmpty()) {
    %>

    <h1>Bookings</h1>
    <table>
        <thead>
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
        </thead>
        <tbody>
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
                <td class="actions">
                    <% if (loggedInAdmin != null) { %>
                        <a href="booking?action=bAview&id=<%= booking.getBookingID() %>">View</a>
                        <a href="booking?action=bAedit&id=<%= booking.getBookingID() %>">Edit</a>
                        <a href="booking?action=bAdelete&id=<%= booking.getBookingID() %>">Delete</a>
                    <% } else if (loggedInCustomer != null && booking.getCustomerID() == loggedInCustomer.getCustomerID()) { %>
                        <a href="booking?action=view&id=<%= booking.getBookingID() %>">View</a>
                        <a href="booking?action=edit&id=<%= booking.getBookingID() %>">Edit</a>
                        <a href="booking?action=delete&id=<%= booking.getBookingID() %>">Delete</a>
                    <% } %>
                </td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <%
        } else {
    %>
    <p>No bookings found.</p>
    <%
        }
    %>

    <a href="booking?action=add" class="btn-add">Add New Booking</a>
    <a href="booking?action=" class="btn-add">Main Menu</a>
</div>

</body>
</html>


