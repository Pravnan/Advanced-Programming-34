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

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Booking (Admin)</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            margin-top: 30px;
            color: #5a5a5a;
        }

        .container {
            max-width: 750px;
            margin: 30px auto;
            background-color: #fff;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"],
        input[type="datetime-local"],
        select {
            padding: 12px 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            width: 100%;
            box-sizing: border-box;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="datetime-local"]:focus,
        select:focus {
            border-color: #4CAF50;
            box-shadow: 0 0 5px rgba(76, 175, 80, 0.5);
            outline: none;
        }

        input[readonly] {
            background-color: #f9f9f9;
            cursor: not-allowed;
        }

        button[type="submit"] {
            padding: 14px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:hover {
            background-color: #45a049;
        }

        .back-button {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 24px;
            background-color: #ccc;
            color: #333;
            text-decoration: none;
            border-radius: 4px;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #bbb;
        }

        @media (max-width: 768px) {
            .container {
                padding: 20px;
            }

            input[type="text"],
            input[type="datetime-local"],
            select {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

    <h1>Edit Booking</h1>

    <div class="container">
        <form action="booking?action=bupdate" method="post">
            <input type="hidden" name="id" value="<%= booking.getBookingID() %>">

            <!-- Booking Details (readonly for admin) -->
            <div>
                <label for="customerID">Customer ID:</label>
                <input type="text" id="customerID" name="customerID" value="<%= booking.getCustomerID() %>" readonly>
            </div>

            <div>
                <label for="destination">Destination:</label>
                <input type="text" id="destination" name="destination" value="<%= booking.getDestination() %>" required>
            </div>

            <div>
                <label for="pickupLocation">Pickup Location:</label>
                <input type="text" id="pickupLocation" name="pickupLocation" value="<%= booking.getPickupLocation() %>" required>
            </div>

            <div>
                <label for="dropoffLocation">Dropoff Location:</label>
                <input type="text" id="dropoffLocation" name="dropoffLocation" value="<%= booking.getDropoffLocation() %>" required>
            </div>

            <div>
                <label for="requestedTime">Requested Time:</label>
                <input type="datetime-local" id="requestedTime" name="requestedTime" value="<%= booking.getRequestedTime() %>" required>
            </div>

            <!-- Admin-specific Fields -->
            <div>
                <label for="totalAmount">Total Amount:</label>
                <input type="text" id="totalAmount" name="totalAmount" value="<%= booking.getTotalAmount() != null ? booking.getTotalAmount().toString() : "" %>" required>
            </div>

            <div>
                <label for="paymentStatus">Payment Status:</label>
                <select id="paymentStatus" name="paymentStatus" required>
                    <option value="PAID" <%= booking.getPaymentStatus().equals("PAID") ? "selected" : "" %>>PAID</option>
                    <option value="PENDING" <%= booking.getPaymentStatus().equals("PENDING") ? "selected" : "" %>>PENDING</option>
                    <option value="CANCELLED" <%= booking.getPaymentStatus().equals("CANCELLED") ? "selected" : "" %>>CANCELLED</option>
                </select>
            </div>

            <!-- Submit Button -->
            <button type="submit">Update Booking</button>
        </form>

        <a class="back-button" href="booking?action=list">Back to Booking List</a>
    </div>

</body>
</html>