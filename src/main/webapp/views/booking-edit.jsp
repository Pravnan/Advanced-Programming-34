<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.vehiclereservationapp.model.Customer" %>
<%@ page import="com.example.vehiclereservationapp.model.Booking" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    Customer customer = (Customer) session.getAttribute("loggedInCustomer");
    Booking booking = (Booking) request.getAttribute("booking");
    if (customer == null || booking == null || booking.getCustomerID() != customer.getCustomerID()) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Booking</title>
    <link rel="stylesheet" href="/styles.css">
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

    <script>
        const locations = [
            { coordinates: "3.0021, 5.0222", distance: 5 },
            { coordinates: "51.5074, -0.1278", distance: 10 },
            { coordinates: "48.8566, 2.3522", distance: 8 },
            { coordinates: "34.0522, -118.2437", distance: 12 },
            { coordinates: "35.6895, 139.6917", distance: 15 }
        ];

        window.onload = function() {
            function populateSelect(selectId, selectedValue) {
                const select = document.getElementById(selectId);
                locations.forEach(function(location) {
                    const option = document.createElement('option');
                    option.value = location.coordinates;
                    option.textContent = `${location.coordinates} (Distance: ${location.distance} km)`;
                    if (location.coordinates === selectedValue) {
                        option.selected = true;
                    }
                    select.appendChild(option);
                });
            }

            populateSelect('pickupLocation', "<%= booking.getPickupLocation() %>");
            populateSelect('dropoffLocation', "<%= booking.getDropoffLocation() %>");
        };
    </script>
</head>
<body>

    <h1>Edit Booking</h1>

    <div class="container">
        <form action="booking?action=update" method="post">
            <input type="hidden" name="id" value="<%= booking.getBookingID() %>">
            <input type="hidden" name="customerID" value="<%= booking.getCustomerID() %>">

            <div>
                <label for="destination">Destination:</label>
                <input type="text" id="destination" name="destination" value="<%= booking.getDestination() %>" required>
            </div>

            <div>
                <label for="pickupLocation">Pickup Location:</label>
                <select id="pickupLocation" name="pickupLocation" required>
                    <option value="">-- Select Pickup Location --</option>
                </select>
            </div>

            <div>
                <label for="dropoffLocation">Dropoff Location:</label>
                <select id="dropoffLocation" name="dropoffLocation" required>
                    <option value="">-- Select Dropoff Location --</option>
                </select>
            </div>

            <div>
                <label for="requestedTime">Requested Time:</label>
                <input type="datetime-local" id="requestedTime" name="requestedTime" value="<%= booking.getRequestedTime() %>" required>
            </div>

            <button type="submit">Update Booking</button>
        </form>

        <a class="back-button" href="booking?action=list">Back to List</a>
    </div>

</body>
</html>