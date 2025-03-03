<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register New Customer</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Register New Customer</h1>
<form action="customer?action=add" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <label for="address">Address:</label>
    <input type="text" id="address" name="address" required><br>

    <label for="nic">NIC:</label>
    <input type="text" id="nic" name="nic" required><br>

    <label for="phoneNumber">Phone Number:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" required><br>

    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <label for="confirmPassword">Confirm Password:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required><br>

    <button type="submit">Register</button>
</form>
<a href="index.jsp">Back to Home</a>
</body>
</html>
