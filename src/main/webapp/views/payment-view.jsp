<%@ page import="com.example.vehiclereservationapp.model.Payment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>View Payment</title>
</head>
<body>
<h2>Payment Details</h2>
<%
  Payment payment = (Payment) request.getAttribute("payment");
  if (payment != null) {
%>
<p><strong>Payment ID:</strong> <%= payment.getPaymentID() %></p>
<p><strong>Booking ID:</strong> <%= payment.getBookingID() %></p>
<p><strong>Amount:</strong> <%= payment.getAmount() %></p>
<p><strong>Payment Method:</strong> <%= payment.getPaymentMethod() %></p>
<p><strong>Payment Reference:</strong> <%= payment.getPaymentReference() %></p>
<p><strong>Payment Date:</strong> <%= payment.getPaymentDate() %></p>
<% } else { %>
<p>Payment not found.</p>
<% } %>
<a href="payment?action=add">Back to List</a>
</body>
</html>
