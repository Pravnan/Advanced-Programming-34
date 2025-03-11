<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.vehiclereservationapp.model.Customer" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
  Customer customer = (Customer) session.getAttribute("loggedInCustomer");
  if (customer == null) {
    response.sendRedirect("login.jsp");
    return;
  }

  String[] dropoffLocations = { "Airport", "Jaffna", "Anuradapura", "Batticaloa", "Galle" };


  String[][] vehicles = {
          {"SUV", "images/suv.png"},
          {"Van", "images/minivan.png"},
          {"PickUp", "images/pickup.png"},
          {"Crossover", "images/crossover.png"},
          {"Sedan", "images/sedan.png"}
  };

  // Default selected vehicle
  String selectedVehicle = request.getParameter("vehicleType") != null ? request.getParameter("vehicleType") : "SUV";
  String selectedImage = "images/suv.png";

  for (String[] vehicle : vehicles) {
    if (vehicle[0].equals(selectedVehicle)) {
      selectedImage = vehicle[1];
      break;
    }
  }
%>

<html>
<head>
  <title>Add New Booking</title>
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
      max-width: 600px;
      margin: 0 auto;
      background-color: #fff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    form {
      display: flex;
      flex-direction: column;
    }
    label {
      margin: 10px 0 5px;
      font-weight: bold;
    }
    input[type="text"],
    input[type="datetime-local"],
    select {
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    button {
      background-color: #4CAF50;
      color: white;
      padding: 10px 15px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px;
      margin-top: 10px;
    }
    button:hover {
      background-color: #45a049;
    }
    a {
      display: block;
      text-align: center;
      margin-top: 20px;
      text-decoration: none;
      color: #4CAF50;
      font-size: 16px;
    }
    a:hover {
      text-decoration: underline;
    }
    .vehicle-image {
      text-align: center;
      margin-top: 10px;
    }
    .vehicle-image img {
      width: 200px;
      height: auto;
    }
  </style>

  <script>

    function updateVehicleImage() {
      var vehicleType = document.getElementById("vehicleType").value;
      var vehicleImages = {
        "SUV": "images/suv.png",
        "Van": "images/minivan.png",
        "Sedan": "images/sedan.png",
        "Crossover": "images/crossover.png",
        "PickUp": "images/pickup.png"
      };
      var imagePath = vehicleImages[vehicleType];
      document.getElementById("vehicleImage").src = imagePath;
    }
  </script>
</head>
<body>

<h1>Add New Booking</h1>

<div class="container">
  <form action="add_booking.jsp" method="get">
    <label for="vehicleType">Vehicle Type:</label>
    <select id="vehicleType" name="vehicleType" required onchange="updateVehicleImage()">
      <option value="">-- Select Vehicle Type --</option>
      <%
        for (String[] vehicle : vehicles) {
      %>
      <option value="<%= vehicle[0] %>" <%= vehicle[0].equals(selectedVehicle) ? "selected" : "" %>>
        <%= vehicle[0] %>
      </option>
      <% } %>
    </select>
  </form>

  <!-- Display the selected vehicle image -->
  <div class="vehicle-image">
    <img id="vehicleImage" src="<%= selectedImage %>" alt="Selected Vehicle">
  </div>

  <form action="booking?action=add" method="post">
    <!-- Auto-fill Customer ID from session -->
    <input type="hidden" name="customerID" value="<%= customer.getCustomerID() %>">

    <label for="destination">Destination:</label>
    <input type="text" id="destination" name="destination" required>

    <label for="pickupLocation">Pickup Location:</label>
    <select id="pickupLocation" name="pickupLocation" required>
      <option value="">-- Select Pickup Location --</option>
      <%
        // List of pickup locations
        String[] pickupLocations = { "Wellawatte", "Dehiwala", "Bambalapitiya", "Kolpitiya", "Pettah" };

        for (String location : pickupLocations) {
      %>
      <option value="<%= location %>">
        <%= location %>
      </option>
      <% } %>
    </select>

    <label for="dropoffLocation">Dropoff Location:</label>
    <select id="dropoffLocation" name="dropoffLocation" required>
      <option value="">-- Select Dropoff Location --</option>
      <%
        for (String location : dropoffLocations) {
          int distance = (int)(Math.random() * 16) + 5; // Generate random distance between 5 and 20
      %>
      <option value="<%= location %>">
        <%= location %> (Distance: <%= distance %> km)
      </option>
      <% } %>
    </select>

    <label for="requestedTime">Requested Time:</label>
    <input type="datetime-local" id="requestedTime" name="requestedTime" required>

    <!-- Remove totalAmount input since it will be set by Admin later -->
    <input type="hidden" name="totalAmount" value="null">

    <button type="submit">Request Booking</button>
  </form>

  <a href="booking?action=list">Back to List</a>
</div>

</body>
</html>
