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
    <title>Edit Customer</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Edit Customer</h1>
<form action="customer?action=update" method="post">
    <input type="hidden" name="id" value="${customer.customerID}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${customer.name}" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${customer.email}" required><br>

    <label for="address">Address:</label>
    <input type="text" id="address" name="address" value="${customer.address}" required><br>

    <label for="nic">NIC:</label>
    <input type="text" id="nic" name="nic" value="${customer.nic}" required><br>

    <label for="phoneNumber">Phone Number:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" value="${customer.phoneNumber}" required><br>

    <button type="submit">Update Customer</button>
</form>
<a href="customer?action=list">Back to List</a>
</body>
</html>
