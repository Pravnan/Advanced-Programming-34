<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Payment</title>
</head>
<body>
<h2>Add Payment</h2>
<form action="payment" method="post">
    <input type="hidden" name="action" value="save">
    <label>Booking ID:</label>
    <input type="number" name="bookingID" required><br>

    <label>Amount:</label>
    <input type="text" name="amount" required><br>

    <label>Payment Method:</label>
    <select name="paymentMethod">
        <option value="Credit Card">Credit Card</option>
        <option value="PayPal">PayPal</option>
        <option value="Bank Transfer">Bank Transfer</option>
    </select><br>

    <label>Payment Reference:</label>
    <input type="text" name="paymentReference"><br>

    <input type="submit" value="Add Payment">
</form>
<a href="payment?action=list">Back to List</a>
</body>
</html>
