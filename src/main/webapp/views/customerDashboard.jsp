<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.vehiclereservationapp.model.Customer" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
  // Retrieve the logged-in customer from session
  Customer customer = (Customer) session.getAttribute("loggedInCustomer");

  if (customer == null) {
    response.sendRedirect("login.jsp"); // Redirect to login if not logged in
    return;
  }
%>

<html>
<head>
  <title>Customer Dashboard</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f9;
      color: #333;
      margin: 0;
      padding: 0;
    }

    h2 {
      text-align: center;
      margin-top: 30px;
      color: #5a5a5a;
    }

    .container {
      max-width: 1440px;
      margin: 30px auto;
      background-color: #fff;
      padding: 40px;
      border-radius: 8px;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    }

    p {
      margin: 15px 0;
      line-height: 1.5;
      font-size: 16px;
    }

    strong {
      color: #5a5a5a;
    }

    .card-container {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: 20px;
      margin-top: 40px;
    }

    .card {
      background-color: #ffffff;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      padding: 20px;
      text-align: center;
      transition: transform 0.3s ease, box-shadow 0.3s ease;
      cursor: pointer;
      text-decoration: none;
      color: #333;
    }

    .card:hover {
      transform: translateY(-5px);
      box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
    }

    .card h3 {
      margin-bottom: 10px;
      color: #4CAF50;
      font-size: 20px;
    }

    .card p {
      font-size: 14px;
      color: #777;
      margin-bottom: 15px;
    }

    .logout-card {
      border-top: 4px solid #e74c3c;
    }

    .logout-card h3 {
      color: #e74c3c;
    }
  </style>
</head>
<body>

  <h2>Welcome, <%= customer.getName() %>!</h2>

  <div class="container">
    <p><strong>Email:</strong> <%= customer.getEmail() %></p>
    <p><strong>Phone Number:</strong> <%= customer.getPhoneNumber() %></p>
    <p><strong>Address:</strong> <%= customer.getAddress() %></p>
    <p><strong>Last Login:</strong> <%= customer.getLastLogin() != null ? customer.getLastLogin().toString() : "N/A" %></p>

    <div class="card-container">
      <a class="card" href="<%= request.getContextPath() %>/customer?action=edit&id=<%= customer.getCustomerID() %>">
        <h3>Edit Profile</h3>
        <p>Update your personal details and contact information.</p>
      </a>

      <a class="card" href="<%= request.getContextPath() %>/booking?action=add">
        <h3>Add Booking</h3>
        <p>Schedule a new service or appointment quickly.</p>
      </a>

      <a class="card" href="<%= request.getContextPath() %>/booking?action=list">
        <h3>View Bookings</h3>
        <p>See your upcoming and past bookings all in one place.</p>
      </a>

      <a class="card logout-card" href="<%= request.getContextPath() %>/login?action=login">
        <h3>Logout</h3>
        <p>Sign out of your account securely.</p>
      </a>
    </div>
  </div>

</body>
</html>
