<%@ page import="java.util.List" %>
<%@ page import="com.example.vehiclereservationapp.model.Payment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Payment List</title>
</head>
<body>
<h2>Payment List</h2>
<table border="1">
  <tr>
    <th>Payment ID</th>
    <th>Booking ID</th>
    <th>Amount</th>
    <th>Payment Method</th>
    <th>Payment Date</th>
    <th>Actions</th>
  </tr>
  <%
    List<Payment> payments = (List<Payment>) request.getAttribute("payments");
    if (payments != null) {
      for (Payment payment : payments) {
  %>
  <tr>
    <td><%= payment.getPaymentID() %></td>
    <td><%= payment.getBookingID() %></td>
    <td><%= payment.getAmount() %></td>
    <td><%= payment.getPaymentMethod() %></td>
    <td><%= payment.getPaymentDate() %></td>
    <td>
      <a href="payment?action=view&id=<%= payment.getPaymentID() %>">View</a>
      <a href="payment?action=edit&id=<%= payment.getPaymentID() %>">Edit</a>
      <a href="payment?action=delete&id=<%= payment.getPaymentID() %>" onclick="return confirm('Are you sure?')">Delete</a>
    </td>
  </tr>
  <%
    }
  } else {
  %>
  <tr>
    <td colspan="6">No payments found.</td>
  </tr>
  <% } %>
</table>
<a href="payment?action=add">Add New Payment</a>
</body>
</html>
