<%@ page import="com.example.vehiclereservationapp.model.Payment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit Payment</title>
</head>
<body>
<h2>Edit Payment</h2>
<%
  Payment payment = (Payment) request.getAttribute("payment");
  if (payment != null) {
%>
<form action="payment" method="post">
  <input type="hidden" name="action" value="update">
  <input type="hidden" name="paymentID" value="<%= payment.getPaymentID() %>">

  <label>Booking ID:</label>
  <input type="number" name="bookingID" value="<%= payment.getBookingID() %>" required><br>

  <label>Amount:</label>
  <input type="text" name="amount" value="<%= payment.getAmount() %>" required><br>

  <label>Payment Method:</label>
  <select name="paymentMethod">
    <option value="Credit Card" <%= "Credit Card".equals(payment.getPaymentMethod()) ? "selected" : "" %>>Credit Card</option>
    <option value="PayPal" <%= "PayPal".equals(payment.getPaymentMethod()) ? "selected" : "" %>>PayPal</option>
    <option value="Bank Transfer" <%= "Bank Transfer".equals(payment.getPaymentMethod()) ? "selected" : "" %>>Bank Transfer</option>
  </select><br>

  <label>Payment Reference:</label>
  <input type="text" name="paymentReference" value="<%= payment.getPaymentReference() %>"><br>

  <input type="submit" value="Update Payment">
</form>
<% } else { %>
<p>Payment not found.</p>
<% } %>
<a href="payment?action=list">Back to List</a>
</body>
</html>
