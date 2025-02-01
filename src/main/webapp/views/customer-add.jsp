<%--
  Created by IntelliJ IDEA.
  User: Mirunan
  Date: 2025-01-27
  Time: 12:17 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Customer</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Add Customer</h1>
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

    <button type="submit">Add Customer</button>
</form>
<a href="customer?action=list">Back to List</a>
</body>
</html>
