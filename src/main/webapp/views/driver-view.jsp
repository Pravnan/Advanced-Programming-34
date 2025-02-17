<%--
  Created by IntelliJ IDEA.
  User: Mirunan
  Date: 2025-01-31
  Time: 4:14 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Driver Details</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Driver Details</h1>
<p><strong>ID:</strong> ${driver.driverID}</p>
<p><strong>Name:</strong> ${driver.name}</p>
<p><strong>License Number:</strong> ${driver.licenseNumber}</p>
<p><strong>Phone Number:</strong> ${driver.phoneNumber}</p>
<p><strong>Assigned Car ID:</strong> ${driver.assignedCarID}</p>
<p><strong>Status:</strong> ${driver.status}</p>
<a href="driver?action=list">Back to List</a>
</body>
</html>

