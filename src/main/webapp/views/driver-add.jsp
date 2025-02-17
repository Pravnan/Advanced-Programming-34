<%--
  Created by IntelliJ IDEA.
  User: Mirunan
  Date: 2025-01-31
  Time: 4:11 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Driver</title>
    <link rel="stylesheet" href="/styles.css">
</head>
<body>
<h1>Add Driver</h1>
<form action="driver?action=add" method="post">
    <label for="Name">Name:</label>
    <input type="text" id="Name" name="Name" required><br>

    <label for="LicenseNumber">License Number:</label>
    <input type="text" id="LicenseNumber" name="LicenseNumber" required><br>

    <label for="PhoneNumber">Phone Number:</label>
    <input type="text" id="PhoneNumber" name="PhoneNumber" required><br>

    <label for="AssignedCarID">Assigned Car ID:</label>
    <input type="number" id="AssignedCarID" name="AssignedCarID"><br>

    <label for="Status">Status:</label>
    <select id="Status" name="Status">
        <option value="Active">Active</option>
        <option value="Inactive">Inactive</option>
    </select><br>

    <label for="Username">Assigned Car ID:</label>
    <input type="text" id="Username" name="Username"><br>

    <label for="PasswordHash">Password :</label>
    <input type="text" id="PasswordHash" name="PasswordHash"><br>

    <label for="Role">Assigned Car ID:</label>
    <input type="text" id="Role" name="Role"><br>

    <button type="submit">Add Driver</button>
</form>
<a href="driver?action=list">Back to List</a>
</body>
</html>y>

</body>
</html>
