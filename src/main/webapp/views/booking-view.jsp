<%@ page import="com.example.vehiclereservationapp.model.Booking" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booking Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
        }

        .container {
            background-color: #ffffff;
            padding: 30px 40px;
            margin: 40px 0;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
            box-sizing: border-box;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table th,
        table td {
            padding: 12px 15px;
            text-align: left;
        }

        table th {
            background-color: #424240;
            color: #ffffff;
            font-weight: bold;
            width: 40%;
            border: 1px solid #e0e0e0;
        }

        table td {
            background-color: #f9f9f9;
            border: 1px solid #e0e0e0;
            color: #333;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            gap: 10px;
        }

        .button-container a {
            text-decoration: none;
            background-color: #4CAF50;
            color: #ffffff;
            padding: 12px 20px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            text-align: center;
            flex: 1;
        }

        .button-container a:hover {
            background-color: #3b783d;
        }

        p {
            text-align: center;
            color: #ff3333;
            font-weight: bold;
        }

        @media (max-width: 480px) {
            .container {
                padding: 20px;
            }

            table th,
            table td {
                padding: 10px;
                font-size: 14px;
            }

            .button-container a {
                padding: 10px;
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Booking Details</h1>

        <%
            Booking booking = (Booking) request.getAttribute("booking");
            if (booking != null) {
        %>

        <table>
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

        <div class="button-container">
            <a href="booking?action=edit&id=<%= booking.getBookingID() %>">Edit</a>
            <a href="booking?action=list">Back to List</a>
        </div>

        <%
            } else {
        %>

        <p>Booking not found.</p>
        <div class="button-container">
            <a href="booking?action=list">Back to List</a>
        </div>

        <%
            }
        %>

    </div>

</body>
</html>

