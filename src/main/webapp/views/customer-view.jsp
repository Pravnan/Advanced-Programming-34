<%--
  Created by IntelliJ IDEA.
  User: Mirunan
  Date: 2025-01-25
  Time: 6:03 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Details</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Customer Details</h1>
<p><strong>ID:</strong> ${customer.customerID}</p>
<p><strong>Name:</strong> ${customer.name}</p>
<p><strong>Email:</strong> ${customer.email}</p>
<p><strong>Address:</strong> ${customer.address}</p>
<p><strong>NIC:</strong> ${customer.nic}</p>
<p><strong>Phone Number:</strong> ${customer.phoneNumber}</p>
<p><strong>Created At:</strong> ${customer.createdAt}</p>
<a href="customer?action=list">Back to List</a>
</body>
</html>

