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
  <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h2>Welcome, <%= customer.getName() %>!</h2>

<p><strong>Email:</strong> <%= customer.getEmail() %></p>
<p><strong>Phone Number:</strong> <%= customer.getPhoneNumber() %></p>
<p><strong>Address:</strong> <%= customer.getAddress() %></p>
<p><strong>Last Login:</strong> <%= customer.getLastLogin() != null ? customer.getLastLogin().toString() : "N/A" %></p>

<ul>
  <li><a href="<%= request.getContextPath() %>/customer?action=edit&id=<%= customer.getCustomerID() %>">Edit Profile</a></li>
  <li><a href="<%= request.getContextPath() %>/booking?action=add">Add Booking</a></li>
  <li><a href="<%= request.getContextPath() %>/booking?action=list">View All Bookings</a></li>
  <li><a href="logout">Logout</a></li>
</ul>
</body>
</html>
