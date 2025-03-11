<%--
  Created by IntelliJ IDEA.
  User: Mirunan
  Date: 2025-01-25
  Time: 6:03 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Details</title>
</head>
<body>

    <h1>Customer Details</h1>

    <div class="container">

        <div class="details">
            <p><strong>ID:</strong> ${customer.customerID}</p>
            <p><strong>Name:</strong> ${customer.name}</p>
            <p><strong>Email:</strong> ${customer.email}</p>
            <p><strong>Address:</strong> ${customer.address}</p>
            <p><strong>NIC:</strong> ${customer.nic}</p>
            <p><strong>Phone Number:</strong> ${customer.phoneNumber}</p>
            <p><strong>Created At:</strong> ${customer.createdAt}</p>
        </div>

        <a class="back-button" href="customer?action=list">Back to List</a>

    </div>

</body>
</html>


