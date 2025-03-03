<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.vehiclereservationapp.model.Customer" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
  Customer customer = (Customer) session.getAttribute("loggedInCustomer");
  if (customer == null) {
    response.sendRedirect("login.jsp");
    return;
  }
%>

<html>
<head>
  <title>Add New Booking</title>
  <link rel="stylesheet" href="/styles.css">

  <!-- Location data as a JavaScript array -->
  <script>
    const locations = [
      { coordinates: "3.0021, 5.0222", distance: 5 },
      { coordinates: "51.5074, -0.1278", distance: 10 },
      { coordinates: "48.8566, 2.3522", distance: 8 },
      { coordinates: "34.0522, -118.2437", distance: 12 },
      { coordinates: "35.6895, 139.6917", distance: 15 }
    ];

    window.onload = function() {
      // Populate Pickup Location options
      const pickupSelect = document.getElementById('pickupLocation');
      locations.forEach(function(location) {
        const option = document.createElement('option');
        option.value = location.coordinates;
        option.textContent = `${location.coordinates} (Distance: ${location.distance} km)`;
        pickupSelect.appendChild(option);
      });

      // Populate Dropoff Location options
      const dropoffSelect = document.getElementById('dropoffLocation');
      locations.forEach(function(location) {
        const option = document.createElement('option');
        option.value = location.coordinates;
        option.textContent = `${location.coordinates} (Distance: ${location.distance} km)`;
        dropoffSelect.appendChild(option);
      });
    };
  </script>
</head>
<body>
<h1>Add New Booking</h1>

<form action="booking?action=add" method="post">
  <!-- Auto-fill Customer ID from session -->
  <input type="hidden" name="customerID" value="<%= customer.getCustomerID() %>">

  <label for="destination">Destination:</label>
  <input type="text" id="destination" name="destination" required><br><br>

  <label for="pickupLocation">Pickup Location:</label>
  <select id="pickupLocation" name="pickupLocation" required>
    <option value="">-- Select Pickup Location --</option>
    <!-- JavaScript will populate the options dynamically -->
  </select>
  <br><br>

  <label for="dropoffLocation">Dropoff Location:</label>
  <select id="dropoffLocation" name="dropoffLocation" required>
    <option value="">-- Select Dropoff Location --</option>
    <!-- JavaScript will populate the options dynamically -->
  </select>
  <br><br>

  <label for="requestedTime">Requested Time:</label>
  <input type="datetime-local" id="requestedTime" name="requestedTime" required><br><br>

  <!-- Remove totalAmount input since it will be set by Admin later -->
  <input type="hidden" name="totalAmount" value="null">

  <button type="submit">Request Booking</button>
</form>

<a href="booking?action=list">Back to List</a>

</body>
</html>
