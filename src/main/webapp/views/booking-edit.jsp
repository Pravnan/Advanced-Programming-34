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

<form action="booking?action=update" method="post">
    <input type="hidden" name="id" value="<%= booking.getBookingID() %>">
    <input type="hidden" name="customerID" value="<%=booking.getCustomerID()%>">

    <label for="destination">Destination:</label>
    <input type="text" id="destination" name="destination" value="<%= booking.getDestination() %>" required><br><br>

    <label for="pickupLocation">Pickup Location:</label>
    <select id="pickupLocation" name="pickupLocation" required>
        <option value="">-- Select Pickup Location --</option>
    </select>
    <br><br>

    <label for="dropoffLocation">Dropoff Location:</label>
    <select id="dropoffLocation" name="dropoffLocation" required>
        <option value="">-- Select Dropoff Location --</option>
    </select>
    <br><br>

    <label for="requestedTime">Requested Time:</label>
    <input type="datetime-local" id="requestedTime" name="requestedTime" value="<%= booking.getRequestedTime() %>" required><br><br>

    <button type="submit">Update Booking</button>
</form>

<a href="booking?action=list">Back to List</a>

</body>
</html>
