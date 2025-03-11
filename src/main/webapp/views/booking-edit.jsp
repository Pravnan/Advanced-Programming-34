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

<html>
<head>
    <title>Edit Booking</title>
    <link rel="stylesheet" href="/styles.css">

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f7f9fc;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: top;
            height: 100vh;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #333;
        }

    form {
        background-color: #fff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 800px; /* Ensure form is not too wide */
        margin: 0 auto;
    }

        label {
            font-size: 14px;
            color: #333;
            font-weight: bold;
            margin-bottom: 8px;
            display: block;
        }

        input, select {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }

        input[type="datetime-local"] {
            background-color: #fff;
            color: #333;
        }

        button {
            background-color: #4CAF50; /* Your requested green color */
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 100%;
        }

        button:hover {
            background-color: #388E3C; /* Darker green for hover effect */
        }

        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #388E3C; /* Darker green for hover effect */
        }

        .form-container {
            max-width: 1440px;
            width: 100%;
            margin: 0 auto;
            padding: 20px;
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
    <div class="form-container">

        <form action="booking?action=update" method="post">
        <h1>Edit Booking</h1>
            <input type="hidden" name="id" value="<%= booking.getBookingID() %>">
            <input type="hidden" name="customerID" value="<%= booking.getCustomerID() %>">

            <label for="destination">Destination:</label>
            <input type="text" id="destination" name="destination" value="<%= booking.getDestination() %>" required><br>

            <label for="pickupLocation">Pickup Location:</label>
            <select id="pickupLocation" name="pickupLocation" required>
                <option value="">-- Select Pickup Location --</option>
            </select><br>

            <label for="dropoffLocation">Dropoff Location:</label>
            <select id="dropoffLocation" name="dropoffLocation" required>
                <option value="">-- Select Dropoff Location --</option>
            </select><br>

            <label for="requestedTime">Requested Time:</label>
            <input type="datetime-local" id="requestedTime" name="requestedTime" value="<%= booking.getRequestedTime() %>" required><br>

            <button type="submit">Update Booking</button>
        </form>

        <a href="booking?action=list">Back to List</a>
    </div>
</body>
</html>


